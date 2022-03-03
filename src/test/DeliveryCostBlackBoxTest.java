package test;

import main.DeliveryCalculationException;
import main.DeliveryCostCalculator;
import org.junit.jupiter.api.Test;

public class DeliveryCostBlackBoxTest {

    DeliveryCostCalculator deliveryCost = new DeliveryCostCalculator();
    static final boolean BIG = true;
    static final boolean SMALL = false;
    static final boolean FRAGILE = true;
    static final boolean NOT_FRAGILE = false;
    static final String TRAFFIC_NORMAL = "normal";
    static final String TRAFFIC_MEDIUM = "medium";
    static final String TRAFFIC_HIGH = "high";
    static final String TRAFFIC_HIGHEST = "highest";

    @Test
    public void testDistanceCost() {
        assert deliveryCost.getDeliveryCost(0, BIG, FRAGILE, TRAFFIC_NORMAL) == 550;
        assert deliveryCost.getDeliveryCost(2, BIG, FRAGILE, TRAFFIC_NORMAL) == 550;
        assert deliveryCost.getDeliveryCost(3, BIG, FRAGILE, TRAFFIC_NORMAL) == 600;
        assert deliveryCost.getDeliveryCost(10, BIG, FRAGILE, TRAFFIC_NORMAL) == 600;
        assert deliveryCost.getDeliveryCost(11, BIG, FRAGILE, TRAFFIC_NORMAL) == 700;
        assert deliveryCost.getDeliveryCost(30, BIG, FRAGILE, TRAFFIC_NORMAL) == 700;
        assert deliveryCost.getDeliveryCost(31, BIG, NOT_FRAGILE, TRAFFIC_NORMAL) == 500;
        assert deliveryCost.getDeliveryCost(40000, BIG, NOT_FRAGILE, TRAFFIC_NORMAL) == 500;
        try{
            deliveryCost.getDeliveryCost(-1, BIG, FRAGILE, TRAFFIC_NORMAL);
        } catch (Exception exception) {
            assert exception instanceof DeliveryCalculationException; 
        }
    }

    @Test
    public void testFragileCost() {
        assert deliveryCost.getDeliveryCost(30, BIG, FRAGILE, TRAFFIC_NORMAL) == 700;
        assert deliveryCost.getDeliveryCost(30, BIG, NOT_FRAGILE, TRAFFIC_NORMAL) == 400;
        try {
            deliveryCost.getDeliveryCost(31, BIG, FRAGILE, TRAFFIC_NORMAL);
        } catch (Exception exception) {
            if (exception instanceof DeliveryCalculationException) {
                assert true;
            } else {
                assert false;
            }
        }
    }

    @Test
    public void testSizeCost() {
        assert deliveryCost.getDeliveryCost(31, SMALL, NOT_FRAGILE, TRAFFIC_NORMAL) == 400;
        assert deliveryCost.getDeliveryCost(31, BIG, NOT_FRAGILE, TRAFFIC_NORMAL) == 500;
    }

    @Test
    public void testTrafficLoadRate() {
        assert deliveryCost.getDeliveryCost(31, BIG, NOT_FRAGILE, TRAFFIC_NORMAL) == 500;
        assert deliveryCost.getDeliveryCost(31, BIG, NOT_FRAGILE, TRAFFIC_MEDIUM) == 600;
        assert deliveryCost.getDeliveryCost(31, BIG, NOT_FRAGILE, TRAFFIC_HIGH) == 700;
        assert deliveryCost.getDeliveryCost(31, BIG, NOT_FRAGILE, TRAFFIC_HIGHEST) == 800;
    }
    
    @Test
    public void testMinimalCost() {
        assert deliveryCost.getDeliveryCost(0, SMALL, NOT_FRAGILE, TRAFFIC_NORMAL) == 400;
    }
}