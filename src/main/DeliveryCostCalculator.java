package main;

import java.util.Map;
import java.util.TreeMap;

public class DeliveryCostCalculator {

    private static final TreeMap<Integer, Double> DISTANCE_PRICE = new TreeMap<>();
    private static final TreeMap<String, Double> TRAFFIC_LOAD_RATE = new TreeMap<>();
    private static final double MINIMAL_COST = 400d;
    private static final int MAX_FRAGILE_DISTANCE = 30;
    private static final int MIN_DISTANCE = 0;
    private static final int EQUATOR_LENGTH = 40000;
    private static final double BIG_SIZE_COST = 200d;
    private static final double SMALL_SIZE_COST = 100d;

    public DeliveryCostCalculator() {
        initDistancePrice();
        initTrafficLoadRate();
    }

    public double getDeliveryCost(int distance, boolean isBigSize, boolean isFragile, String trafficLoad) {
        checkFragileDistance(distance, isFragile);
        double cost = getDistanceCost(distance);
        cost += getSizeCost(isBigSize);
        cost += getFragileCost(isFragile);
        cost *= getTrafficRate(trafficLoad);
        return Math.max(cost, MINIMAL_COST);
    }

    protected double getFragileCost(boolean isFragile) {
        return isFragile ? 300 : 0;
    }

    protected double getTrafficRate(String trafficLoad) {
        checkTrafficRate(trafficLoad);
        return TRAFFIC_LOAD_RATE.entrySet()
                .stream()
                .filter(rate -> rate.getKey().equals(trafficLoad))
                .findFirst()
                .get()
                .getValue();
    }

    protected double getDistanceCost(int distance) {
        checkDistance(distance);
        return DISTANCE_PRICE.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .filter(price -> price.getKey() >= distance)
                .findFirst()
                .get()
                .getValue();
    }

    protected double getSizeCost(boolean isBigSize) {
        return isBigSize ? BIG_SIZE_COST : SMALL_SIZE_COST;
    }

    private void checkTrafficRate(String trafficLoad) {
        if (TRAFFIC_LOAD_RATE.entrySet().stream().noneMatch(load -> load.getKey().equals(trafficLoad))) {
            throw new DeliveryCalculationException("Cannot operate with traffic load value: " + trafficLoad + "\n" +
                    "Use one of the following: \n" + TRAFFIC_LOAD_RATE);
        }
    }

    private void checkDistance(int distance) {
        if (distance < MIN_DISTANCE || distance > EQUATOR_LENGTH) {
            throw new DeliveryCalculationException("Distance must be between [" + MIN_DISTANCE + ".." + EQUATOR_LENGTH + "]");
        }
    }

    private void checkFragileDistance(int distance, boolean isFragile) {
        if (isFragile && distance > MAX_FRAGILE_DISTANCE) {
            throw new DeliveryCalculationException("Maximum fragile parcel shipment distance is 30km");
        }
    }

    private static void initDistancePrice() {
        DISTANCE_PRICE.put(2, 50d);
        DISTANCE_PRICE.put(10, 100d);
        DISTANCE_PRICE.put(30, 200d);
        DISTANCE_PRICE.put(65535, 300d);
    }

    private static void initTrafficLoadRate() {
        TRAFFIC_LOAD_RATE.put("normal", 1.0d);
        TRAFFIC_LOAD_RATE.put("medium", 1.2d);
        TRAFFIC_LOAD_RATE.put("high", 1.4d);
        TRAFFIC_LOAD_RATE.put("highest", 1.6d);
    }
}