# Втора лабораториска вежба по Софтверско инженерство

## Виктор Попоски, 121029

###  Control Flow Graph

![alt text](https://github.com/ViktorPxO/SI_2024_lab2_121029/blob/master/CFG.drawio.png?raw=true)

### Цикломатска комплексност

Цикломатската комплексност на овој код е 6, добиена е според формулата M = E - N + 2P.
E = 23 -> број на рабови
N = 19 -> број на јазли
P = 1 -> една функција

### Тест случаи според критериумот  Every statement 

Со овие тест случаи треба да го посетиме секое разгранување во сите јазли за одлука
		
		 //првиот if -> false
        checkCart(null, 100))
        
        //null barcode
        checkCart(List.of(
                        new Item(null, null, 15, 1f)
                ), 1))

        //barcode invalid characters
        checkCart(List.of(
                        new Item("item1", "42762s9387654hhh", 15, 1f)
                ), 1))

        //check has discount -> ќе биде точно доколку се пресмета попустот од 10%, тогаш со payment=95 ќе може да плати
        checkCart(List.of(
                new Item("item1", "333", 100, 0.9f)
        ), 95)

        //check has discount -> резултатот треба да е false, нема попуст па не може да плати
        checkCart(List.of(
                new Item("item1", "333", 100, 1f)
        ), 95)

        //400 со 10% е 360, доколку не се пресметаат дополнителните 30 нема да може да плати
        checkCart(List.of(
                new Item("item3", "023494825", 400, 10.0f)
        ), 350)

        //400 со 10% е 360, доколку не се пресметаат дополнителните 30 нема да може да плати. Еден од условите не е исполнет и нема да се пресметаат.
        checkCart(List.of(
                new Item("item3", "123494825", 400, 10.0f)
        ), 350)

	Со овие тест случаи ќе се помине низ секој исход од секое разгранување.

### Тест случаи според критериумот Every path

(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
	
	checkCart(Arrays.asList(
			new Item("item1", "301101129", 15, 0.9f), //(F, x, x) 
			new Item("item2", "301101129", 315, 0.0f), //(T, F, x)
			new Item("item3", "923494825", 315, 0.9f), //(T, T, F)
			new Item("item3", "023494825", 315, 0.9f) //(T, T, T)
			), 
		1) 
