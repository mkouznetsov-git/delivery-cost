package test;

import main.DeliveryCalculationException;
import main.DeliveryCostCalculator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class DeliveryCostWhiteBoxTest extends DeliveryCostCalculator {

    @Test
    public void testDistanceCostPositive() {
        assert getDistanceCost(0) == 50d;
        assert getDistanceCost(2) == 50d;
        assert getDistanceCost(3) == 100d;
        assert getDistanceCost(10) == 100d;
        assert getDistanceCost(11) == 200d;
        assert getDistanceCost(29) == 200d;
        assert getDistanceCost(30) == 200d;
        assert getDistanceCost(31) == 300d;
        assert getDistanceCost(40000) == 300d;
    }

    @Test
    public void testDistanceCostNegative() {
        assert getDistanceCost(-1) == 50d;
        assert getDistanceCost(65535) == 300d;
    }

    @Test
    public void testFragileCost() {
        assert getFragileCost(true) == 300d;
        assert getFragileCost(false) == 0d;
    }

    @Test
    public void testTrafficRatePositive() {
        assert getTrafficRate("normal") == 1.0f;
        assert getTrafficRate("medium") == 1.2f;
        assert getTrafficRate("high") == 1.4f;
        assert getTrafficRate("highest") == 1.6f;
    }

    @Test
    public void testTrafficRateNegative() {
        List<String> incorrectTrafficLoadValues = asList("", "HIGH", null);
        incorrectTrafficLoadValues.forEach(value -> {
            try {
                System.out.println("Checking [" + value + "]...");
                getTrafficRate(value);
            } catch (Exception exception) {
                assert exception instanceof DeliveryCalculationException;
            }
        });
    }

    @Test
    public void testSizeCost() {
        assert getSizeCost(true) == 200d;
        assert getSizeCost(false) == 100d;
    }
}