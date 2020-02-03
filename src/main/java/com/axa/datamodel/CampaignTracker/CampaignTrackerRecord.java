package com.axa.datamodel.CampaignTracker;


import com.axa.datamodel.Benchmark.BenchMarkRecord;

import java.util.Optional;
import java.util.StringJoiner;

import static com.axa.StringUtil.StringUtil.*;

public class CampaignTrackerRecord {
    public static String HEADER = "Campaign type,Media buy,Start date,End date,Budget,Primary KPI, Impressions ,CPM,Improvement in CPM,Video views,CPV,Improvement in CPV,CTR,Improvement in CTR, Clicks ,CPC,Improvement in CPC,Reactions,Shares,Comments,Total no of engagements,CPE,Improvement in CPE\n";
    private String campaignNature;
    private String mediaBuy;
    private String startDate;
    private String endDate;
    private Optional<Double> budget;
    private String primaryKPI;
    private Optional<Integer> impressions;
    private Optional<Double> CPM;
    private Optional<Double> improvementInCPM;
    private Optional<Integer> videoViews;
    private Optional<Double> CPV;
    private Optional<Double> improvementInCPV;
    private Optional<Double> CTR;
    private Optional<Double> improvementInCTR;
    private Optional<Integer> clicks;
    private Optional<Double> CPC;
    private Optional<Double> improvementInCPC;
    private Optional<Integer> reactions;
    private Optional<Integer> shares;
    private Optional<Integer> comments;
    private Optional<Integer> totalNumberOfEngagement;
    private Optional<Double> CPE;
    private Optional<Double> improvementInCPE;


    public static String getHEADER() {
        return HEADER;
    }

    public static CampaignTrackerRecord createCampaignTracker(String[] metadata) {
        String campaignNature = metadata[0];
        String mediaBuy = metadata[1];
        String startDate = metadata[2];
        String endDate = metadata[3];
        Optional<Double> budget = parseOptionalDollarValue(metadata[4]);
        String buyingUnit = metadata[5];
        Optional<Integer> impressions = parseOptionalIntValue(metadata[6]);
        Optional<Double> CPM = parseOptionalDollarValue(metadata[7]);
        Optional<Double> improvementInCPM = parseOptionalPercentageValue(metadata[8]);
        Optional<Integer> videoViews = parseOptionalIntValue(metadata[9]);
        Optional<Double> CPV = parseOptionalDoubleValue(metadata[10]);
        Optional<Double> improvementInCPV =  parseOptionalPercentageValue(metadata[11]);
        Optional<Double> CTR = parseOptionalPercentageValue(metadata[12]);
        Optional<Double> improvementInCPR = parseOptionalPercentageValue(metadata[13]);
        Optional<Integer> clicks = parseOptionalIntValue(metadata[14]);
        Optional<Double> CPC = parseOptionalDollarValue(metadata[15]);
        Optional<Double> improvementInCPC = parseOptionalPercentageValue(metadata[16]);
        Optional<Integer> reactions = parseOptionalIntValue(metadata[17]);
        Optional<Integer> shares = parseOptionalIntValue(metadata[18]);
        Optional<Integer> comments = parseOptionalIntValue(metadata[19]);
        Optional<Integer> totalEngagement = parseOptionalIntValue(metadata[20]);
        Optional<Double> CPE = parseOptionalDollarValue(metadata[21]);
        Optional<Double> improvementInCPE =  parseOptionalPercentageValue(metadata[22]);

        return new CampaignTrackerRecord(campaignNature, mediaBuy, startDate, endDate, budget, buyingUnit, impressions,
                CPM, improvementInCPM, videoViews, CPV, improvementInCPV, CTR, improvementInCPR, clicks, CPC, improvementInCPC,
                reactions, shares, comments, totalEngagement, CPE, improvementInCPE);
    }

    public String toCSVRowString() {
        StringJoiner stringJoiner = new StringJoiner(",");

        stringJoiner.add(campaignNature)
                .add(mediaBuy)
                .add(startDate)
                .add(endDate)
                .add(getDollarString(budget))
                .add(primaryKPI)
                .add(getIntString(impressions))
                .add(getDollarString(CPM))
                .add(getPercentageString(improvementInCPM))
                .add(getIntString(videoViews))
                .add(getDoubleString(CPV))
                .add(getPercentageString(improvementInCPV))
                .add(getPercentageString(CTR))
                .add(getPercentageString(improvementInCTR))
                .add(getIntString(clicks))
                .add(getDollarString(CPC))
                .add(getPercentageString(improvementInCPC))
                .add(getIntString(reactions))
                .add(getIntString(shares))
                .add(getIntString(comments))
                .add(getIntString(totalNumberOfEngagement))
                .add(getDollarString(CPE))
                .add(getPercentageString(improvementInCPE)).add("\n");
        return stringJoiner.toString();
    }

    public CampaignTrackerRecord(String campaignNature, String mediaBuy, String startDate, String endDate, Optional<Double> budget, String primaryKPI, Optional<Integer> impressions, Optional<Double> CPM, Optional<Double> improvementInCPM, Optional<Integer> videoViews, Optional<Double> CPV, Optional<Double> improvementInCPV, Optional<Double> CTR, Optional<Double> improvementInCTR, Optional<Integer> clicks, Optional<Double> CPC, Optional<Double> improvementInCPC, Optional<Integer> reactions, Optional<Integer> shares, Optional<Integer> comments, Optional<Integer> totalNumberOfEngagement, Optional<Double> CPE, Optional<Double> improvementInCPE) {
        this.campaignNature = campaignNature;
        this.mediaBuy = mediaBuy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.primaryKPI = primaryKPI;
        this.impressions = impressions;
        this.CPM = CPM;
        this.improvementInCPM = improvementInCPM;
        this.videoViews = videoViews;
        this.CPV = CPV;
        this.improvementInCPV = improvementInCPV;
        this.CTR = CTR;
        this.improvementInCTR = improvementInCTR;
        this.clicks = clicks;
        this.CPC = CPC;
        this.improvementInCPC = improvementInCPC;
        this.reactions = reactions;
        this.shares = shares;
        this.comments = comments;
        this.totalNumberOfEngagement = totalNumberOfEngagement;
        this.CPE = CPE;
        this.improvementInCPE = improvementInCPE;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Optional<Double> getBudget() {
        return budget;
    }

    public void setBudget(Optional<Double> budget) {
        this.budget = budget;
    }

    public String getPrimaryKPI() {
        return primaryKPI;
    }

    public void setPrimaryKPI(String primaryKPI) {
        this.primaryKPI = primaryKPI;
    }

    public Optional<Integer> getImpressions() {
        return impressions;
    }

    public void setImpressions(Optional<Integer> impressions) {
        this.impressions = impressions;
    }

    public Optional<Double> getCPM() {
        return CPM;
    }

    public void setCPM(Optional<Double> CPM) {
        this.CPM = CPM;
    }

    public Optional<Double> getImprovementInCPM() {
        return improvementInCPM;
    }

    public void setImprovementInCPM(Optional<Double> improvementInCPM) {
        this.improvementInCPM = improvementInCPM;
    }

    public Optional<Integer> getVideoViews() {
        return videoViews;
    }

    public void setVideoViews(Optional<Integer> videoViews) {
        this.videoViews = videoViews;
    }

    public Optional<Double> getCPV() {
        return CPV;
    }

    public void setCPV(Optional<Double> CPV) {
        this.CPV = CPV;
    }

    public Optional<Double> getImprovementInCPV() {
        return improvementInCPV;
    }

    public void setImprovementInCPV(Optional<Double> improvementInCPV) {
        this.improvementInCPV = improvementInCPV;
    }

    public Optional<Double> getCTR() {
        return CTR;
    }

    public void setCTR(Optional<Double> CTR) {
        this.CTR = CTR;
    }

    public Optional<Double> getImprovementInCTR() {
        return improvementInCTR;
    }

    public void setImprovementInCTR(Optional<Double> improvementInCTR) {
        this.improvementInCTR = improvementInCTR;
    }

    public Optional<Integer> getClicks() {
        return clicks;
    }

    public void setClicks(Optional<Integer> clicks) {
        this.clicks = clicks;
    }

    public Optional<Double> getCPC() {
        return CPC;
    }

    public void setCPC(Optional<Double> CPC) {
        this.CPC = CPC;
    }

    public Optional<Double> getImprovementInCPC() {
        return improvementInCPC;
    }

    public void setImprovementInCPC(Optional<Double> improvementInCPC) {
        this.improvementInCPC = improvementInCPC;
    }

    public Optional<Integer> getReactions() {
        return reactions;
    }

    public void setReactions(Optional<Integer> reactions) {
        this.reactions = reactions;
    }

    public Optional<Integer> getShares() {
        return shares;
    }

    public void setShares(Optional<Integer> shares) {
        this.shares = shares;
    }

    public Optional<Integer> getComments() {
        return comments;
    }

    public void setComments(Optional<Integer> comments) {
        this.comments = comments;
    }

    public Optional<Integer> getTotalNumberOfEngagement() {
        return totalNumberOfEngagement;
    }

    public void setTotalNumberOfEngagement(Optional<Integer> totalNumberOfEngagement) {
        this.totalNumberOfEngagement = totalNumberOfEngagement;
    }

    public Optional<Double> getCPE() {
        return CPE;
    }

    public void setCPE(Optional<Double> CPE) {
        this.CPE = CPE;
    }

    public Optional<Double> getImprovementInCPE() {
        return improvementInCPE;
    }

    public void setImprovementInCPE(Optional<Double> improvementInCPE) {
        this.improvementInCPE = improvementInCPE;
    }

    public void calcImprovementInCPM(Double averageCPM) {
        System.out.println("calc improvement in CPM:");

        if (this.getCPM().isPresent()) {
            Double cpm = this.getCPM().get();
            double improvement = averageCPM < 0.0000001 ? 0 :(averageCPM - cpm )/ averageCPM;
            this.setImprovementInCPM(Optional.of(improvement));
            System.out.println("benchmark: " + averageCPM);
            System.out.println("cpm: " + cpm);
            System.out.println("improvement: " + improvement);
        }
    }

    public void calcImprovementInCPV(Double averageCPV) {
        System.out.println("calc improvement in CPV:");

        if (this.getCPV().isPresent()) {
            Double cpv = this.getCPV().get();
            double improvement = averageCPV < 0.000001 ? 0 :(averageCPV - cpv ) / averageCPV;
            this.setImprovementInCPV(Optional.of(improvement));
            System.out.println("benchmark: " + averageCPV);
            System.out.println("cpv: " + cpv);
            System.out.println("improvement: " + improvement);
        }
    }

    public void calcImprovementInCTR(Double averageCTR) {
        System.out.println("calc improvement in CTR:");

        if (this.getCTR().isPresent()) {
            Double ctr = this.getCTR().get();
            double improvement = averageCTR < 0.000001 ? 0 : (ctr - averageCTR ) / averageCTR ;
            this.setImprovementInCTR(Optional.of(improvement));
            System.out.println("benchmark: " + averageCTR);
            System.out.println("ctr: " + ctr);
            System.out.println("improvement: " + improvement);
        }
    }

    public void calcImprovementInCPC(Double averageCPC) {
        System.out.println("calc improvement in CPC:");

        if (this.getCPC().isPresent()) {
            Double cpc = this.getCPC().get();
            double improvement = averageCPC < 0.000001 ? 0 :(averageCPC - cpc) / averageCPC;
            this.setImprovementInCPC(Optional.of(improvement));
            System.out.println("benchmark: " + averageCPC);
            System.out.println("cpc: " + cpc);
            System.out.println("improvement: " + improvement);
        }
    }

    public void calcImprovementInCPE(Double averageCPE) {
        System.out.println("calc improvement in CPE:");

        if (this.getCPE().isPresent()) {
            Double cpe = this.getCPE().get();
            double improvement = averageCPE < 0.000001 ? 0.0: (averageCPE - cpe) / averageCPE;
            this.setImprovementInCPE(Optional.of(improvement));
            System.out.println("benchmark: " + averageCPE);
            System.out.println("cpe: " + cpe);
            System.out.println("improvement: " + improvement);
        }
    }

    public void calcAndSetImprovement(BenchMarkRecord benchMarkRecord) {
        this.calcImprovementInCPM(benchMarkRecord.getAverageCPM());
        this.calcImprovementInCPV(benchMarkRecord.getAverageCPV());
        this.calcImprovementInCTR(benchMarkRecord.getAverageCTR());
        this.calcImprovementInCPC(benchMarkRecord.getAverageCPC());
        this.calcImprovementInCPE(benchMarkRecord.getAverageCPE());
    }
}
