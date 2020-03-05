package com.github.aman.coronavirustracker.services;


import com.github.aman.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {
    private static String VIRUS_DATA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private List<LocationStats> allStats = new ArrayList<>();
    private String latestDate;
    private int totalReportedCases;
    private int totalNewCases;

    public int getTotalReportedCases() {
        return totalReportedCases;
    }

    public int getTotalNewCases() {
        return totalNewCases;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    public String getLatestDate() {
        return latestDate;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {
        /**will populate currentStats into newStats
         * once currentStats in completely created
         * inorder to avoid synchronisation problems
         */
        List<LocationStats> currentStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(VIRUS_DATA))
                                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String header[] = response.body().toString().split("\n")[0].split(",");
        this.latestDate = header[header.length-1];
        System.out.println(this.latestDate);

        StringReader csvReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            LocationStats currentLocationStats = new LocationStats(record.get("Province/State"), record.get("Country/Region"), Integer.parseInt(record.get(record.size()-1)), Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
            currentStats.add(currentLocationStats);
        }
        currentStats.sort((x,y)->{
            return y.getLatestCount()-x.getLatestCount();
        });
        this.totalReportedCases = currentStats.stream().mapToInt(x->x.getLatestCount()).sum();
        this.totalNewCases = currentStats.stream().mapToInt(x->x.getDayOverDayChange()).sum();
//        currentStats.forEach(x->{
//            totalNewCases+=x.getDayOverDayChange();
//            totalReportedCases+=x.getLatestCount();
//        });
//

        this.allStats = currentStats;
//        System.out.println(allStats);

    }
}
