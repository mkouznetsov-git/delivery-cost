package main;

public class Main {
    
    
    public static void main(String[] args) {
        int distance;
        boolean isBigSize;
        boolean isFragile;
        String trafficLoad;
        try{
            distance = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception){
            throw new RuntimeException("Cannot parse integer value of args[0]: [" + args[0] + "]");
        }
        isBigSize = Boolean.parseBoolean(args[1]);
        isFragile = Boolean.parseBoolean(args[2]);
        trafficLoad = args[3];
        DeliveryCost deliveryCost = new DeliveryCost();
        System.out.println(deliveryCost.getDeliveryCost(distance, isBigSize, isFragile, trafficLoad));
    }
}