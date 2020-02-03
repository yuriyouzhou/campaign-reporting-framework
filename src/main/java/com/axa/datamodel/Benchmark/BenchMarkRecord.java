package com.axa.datamodel.Benchmark;


import com.axa.datamodel.CampaignTracker.CampaignTrackerRecord;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import static com.axa.StringUtil.StringUtil.*;


public class BenchMarkRecord {
    public static final String HEADER = "Campaign nature,Media buy,Count,Average CPM,SD CPM, Average CPV,SD CPV,Average CTR,SD CTR,Average CPC,SD CPC,Average CPE,SD CPE\n";
    private String campaignNature;
    private String mediaBuy;
    private Integer count;
    private Double totalCost;
    private Integer totalClicks;
    private Integer totalEngagement;
    private Integer totalImpressions;
    private Integer totalViews;
    private List<Double> cpms;
    private List<Double> cpvs;
    private List<Double> ctrs;
    private List<Double> cpcs;
    private List<Double> cpes;
    private Double averageCPM; // budget / k impressoins
    private Double averageCPV; // budget / views
    private Double averageCTR; // clicks / impressions
    private Double averageCPC; // budget / clicks
    private Double averageCPE; // budget / engagement

    public BenchMarkRecord(String campaignNature, String mediaBuy, Integer count, Double averageCPM, Double averageCPV, Double averageCTR, Double averageCPC, Double averageCPE) {
        this.campaignNature = campaignNature;
        this.mediaBuy = mediaBuy;
        this.count = count;
        this.averageCPM = averageCPM;
        this.averageCPV = averageCPV;
        this.averageCTR = averageCTR;
        this.averageCPC = averageCPC;
        this.averageCPE = averageCPE;
        this.totalClicks = 0;
        this.totalCost = 0.0;
        this.totalEngagement = 0;
        this.totalImpressions = 0;
        this.totalViews = 0;
        cpms = new LinkedList<>();
        cpvs = new LinkedList<>();
        ctrs = new LinkedList<>();
        cpcs = new LinkedList<>();
        cpes = new LinkedList<>();
    }

    public static String getHEADER() {
        return HEADER;
    }

    public String getCampaignNature() {
        return campaignNature;
    }

    public void setCampaignNature(String campaignNature) {
        this.campaignNature = campaignNature;
    }

    public String getMediaBuy() {
        return mediaBuy;
    }

    public void setMediaBuy(String mediaBuy) {
        this.mediaBuy = mediaBuy;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(Integer totalClicks) {
        this.totalClicks = totalClicks;
    }

    public Integer getTotalEngagement() {
        return totalEngagement;
    }

    public void setTotalEngagement(Integer totalEngagement) {
        this.totalEngagement = totalEngagement;
    }

    public Integer getTotalImpressions() {
        return totalImpressions;
    }

    public void setTotalImpressions(Integer totalImpressions) {
        this.totalImpressions = totalImpressions;
    }

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public List<Double> getCpms() {
        return cpms;
    }

    public void setCpms(List<Double> cpms) {
        this.cpms = cpms;
    }

    public List<Double> getCpvs() {
        return cpvs;
    }

    public void setCpvs(List<Double> cpvs) {
        this.cpvs = cpvs;
    }

    public List<Double> getCtrs() {
        return ctrs;
    }

    public void setCtrs(List<Double> ctrs) {
        this.ctrs = ctrs;
    }

    public List<Double> getCpcs() {
        return cpcs;
    }

    public void setCpcs(List<Double> cpcs) {
        this.cpcs = cpcs;
    }

    public List<Double> getCpes() {
        return cpes;
    }

    public void setCpes(List<Double> cpes) {
        this.cpes = cpes;
    }

    public Double getAverageCPM() {
        return averageCPM;
    }

    public void setAverageCPM(double averageCPM) {
        this.averageCPM = averageCPM;
    }

    public Double getAverageCPV() {
        return averageCPV;
    }

    public void setAverageCPV(double averageCPV) {
        this.averageCPV = averageCPV;
    }

    public Double getAverageCTR() {
        return averageCTR;
    }

    public void setAverageCTR(double averageCTR) {
        this.averageCTR = averageCTR;
    }

    public Double getAverageCPC() {
        return averageCPC;
    }

    public void setAverageCPC(double averageCPC) {
        this.averageCPC = averageCPC;
    }

    public Double getAverageCPE() {
        return averageCPE;
    }

    public void setAverageCPE(double averageCPE) {
        this.averageCPE = averageCPE;
    }

    public static BenchMarkRecord createBenchmark(String[] attributes) {
        String campaignNature = attributes[0];
        String mediaBuy = attributes[1];
        int count = parseOptionalIntValue(attributes[2]).orElse(0);
        double averageCPM = parseOptionalDollarValue(attributes[3]).orElse(0.0);
        double averageCPV = parseOptionalDollarValue(attributes[5]).orElse(0.0);
        double averageCTR = parseOptionalPercentageValue(attributes[7]).orElse(0.0);
        double averageCPC = parseOptionalDollarValue(attributes[9]).orElse(0.0);
        double averageCPE = parseOptionalDollarValue(attributes[11]).orElse(0.0);
        return new BenchMarkRecord(campaignNature, mediaBuy, count, averageCPM, averageCPV, averageCTR, averageCPC, averageCPE);
    }



    private static double calcSD(List<Double> values) {
        double sum=0;
        double finalsum = 0;
        double average = 0;

        for( Double i : values) {
            finalsum = (sum += i);
        }

        average = finalsum/(values.size());

        double sumX=0;
        double finalsumX=0;
        double[] x1_average = new double[2000];
        for (int i = 0; i<values.size(); i++){
            double fvalue = (Math.pow((values.get(0) - average), 2));
            x1_average[i]= fvalue;
        }

        for(double i : x1_average) {
            finalsumX = (sumX += i);
        }

        Double AverageX = finalsumX/(values.size());
        return Math.sqrt(AverageX);
    }

    public String toCSVRowString() {
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add(campaignNature).add(mediaBuy)
                .add(getIntString(Optional.of(count)))
                .add(getDollarString(Optional.of(averageCPM)))
                .add(getDoubleString(Optional.of(calcSD(cpms))))
                .add(getDollarString(Optional.of(averageCPV)))
                .add(getDoubleString(Optional.of(calcSD(cpvs))))
                .add(getPercentageString(Optional.of(averageCTR)))
                .add(getDoubleString(Optional.of(calcSD(ctrs))))
                .add(getDollarString(Optional.of(averageCPC)))
                .add(getDoubleString(Optional.of(calcSD(cpcs))))
                .add(getDollarString(Optional.of(averageCPE)))
                .add(getDoubleString(Optional.of(calcSD(cpes))))
                .add("\n");
        return stringJoiner.toString();    }

    public void updateRecord(CampaignTrackerRecord campaignTrackerRecord) {
        this.setCount(this.getCount() + 1);
        this.setTotalImpressions(this.getTotalImpressions()
                + campaignTrackerRecord.getImpressions().orElse(0));
        this.setTotalEngagement(this.getTotalEngagement()
                + campaignTrackerRecord.getTotalNumberOfEngagement().orElse(0));
        this.setTotalCost(this.getTotalCost()
                + campaignTrackerRecord.getBudget().orElse(0.0000));
        this.setTotalViews(this.getTotalViews()
                + campaignTrackerRecord.getVideoViews().orElse(0));
        this.setTotalClicks(this.getTotalClicks()
                + campaignTrackerRecord.getClicks().orElse(0));
        this.getCpcs()
                .add(campaignTrackerRecord.getCPC().orElse(0.0000));
        this.getCpes()
                .add(campaignTrackerRecord.getCPE().orElse(0.0000));
        this.getCpms()
                .add(campaignTrackerRecord.getCPM().orElse(0.0000));
        this.getCpvs()
                .add(campaignTrackerRecord.getCPV().orElse(0.0000));
        this.getCtrs()
                .add(campaignTrackerRecord.getCTR().orElse(0.0000));
    }

    public void calcAndSetAverageValues() {
        this.setAverageCPM(this.getTotalImpressions() == 0? 0.0: this.getTotalCost() / ((double) (this.getTotalImpressions() / 1000)));
        this.setAverageCPV(this.getTotalViews() == 0? 0.0: Double.valueOf(this.getTotalClicks()) / this.getTotalViews());
        this.setAverageCPE(this.getTotalEngagement() == 0? 0.0: this.getTotalCost() / this.getTotalEngagement());
        this.setAverageCPC(this.getTotalClicks() == 0 ? 0.0: this.getTotalCost() / this.getTotalClicks());
        this.setAverageCTR(this.getTotalImpressions() == 0? 0.0: Double.valueOf(this.getTotalClicks()) / Double.valueOf(this.getTotalImpressions()));
    }
}
