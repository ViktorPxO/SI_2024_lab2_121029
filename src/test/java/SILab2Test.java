import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SILab2Test {

    @Test
    public void testZa4() {
        //првиот if -> false
        RuntimeException exceptionNullList = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, 100));
        assertEquals("allItems list can't be null!", exceptionNullList.getMessage());

        //null barcode
        RuntimeException exceptionNullBarcode = assertThrows(RuntimeException.class, () ->
        SILab2.checkCart(List.of(
                        new Item(null, null, 15, 0f)
                ), 1));
        assertEquals("No barcode!", exceptionNullBarcode.getMessage());

        //barcode invalid characters
        RuntimeException exceptionInvalidBarcode = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(
                        new Item("item1", "42762s9387654hhh", 15, 0f)
                ), 1));
        assertEquals("Invalid character in item barcode!", exceptionInvalidBarcode.getMessage());

        //check has discount -> ќе биде точно доколку се пресмета попустот од 10%, тогаш со payment=95 ќе може да плати
        boolean resultWithDiscount = SILab2.checkCart(List.of(
                new Item("item1", "333", 100, 0.9f)
        ), 95);
        assertEquals(resultWithDiscount, true);

        //check has discount -> резултатот треба да е false, нема попуст па не може да плати
        boolean resultWithoutDiscount = SILab2.checkCart(List.of(
                new Item("item1", "333", 100, 0f)
        ), 95);
        assertEquals(resultWithoutDiscount, false);

        //400 со 10% е 360, доколку не се пресметаат дополнителните 30 нема да може да плати
        boolean resultWith30AtEnd = SILab2.checkCart(List.of(
                new Item("item3", "023494825", 400, 0.9f)
        ), 350);
        assertEquals(resultWith30AtEnd, true);

        //400 со 10% е 360, доколку не се пресметаат дополнителните 30 нема да може да плати. Еден од условите не е исполнет и нема да се пресметаат.
        boolean resultWithout30AtEnd = SILab2.checkCart(List.of(
                new Item("item3", "123494825", 400, 0f)
        ), 350);
        assertEquals(resultWithout30AtEnd, false);
    }

    @Test
    public void testZa5() {
        boolean resultFXX = SILab2.checkCart(List.of(
                new Item("item1", "301101129", 200, 0.9f)
        ), 170);
        assertEquals(resultFXX, false);

        boolean resultTFX = SILab2.checkCart(List.of(
                new Item("item2", "301101129", 400, 0.0f)
        ), 350);
        assertEquals(resultTFX, false);

        boolean resultTTF= SILab2.checkCart(List.of(
                new Item("item3", "923494825", 400, 0.9f)
        ), 350);
        assertEquals(resultTTF, false);

        boolean resultTTT= SILab2.checkCart(List.of(
                new Item("item3", "023494825", 400, 0.9f)
        ), 350);
        assertEquals(resultTTT, true);
    }


}
