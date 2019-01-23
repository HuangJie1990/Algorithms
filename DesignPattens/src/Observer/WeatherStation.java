package Observer;

import java.util.Random;


public class WeatherStation {
    public static void main(String args[]) {
/*        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay();
        weatherData.registerObserver(currentConditionDisplay);

        int i = 0;
        Random random = new Random();
        while (true) {
            float temperature = random.nextInt() % 100;
            float humidity = random.nextInt() % 100;
            float pressure = random.nextInt() % 100;
            weatherData.setMeasurements(temperature, humidity, pressure);
            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 10) {
                weatherData.removeObserver(currentConditionDisplay);
            }
        }*/


        WeatherDataObservable weatherDataObservable = new WeatherDataObservable();
        CurrentConditionsDisplayObserver currentConditionsDisplayObserver = new CurrentConditionsDisplayObserver(weatherDataObservable);

        int i = 0;
        Random random = new Random();
        while (true) {
            float temperature = random.nextInt() % 100;
            float humidity = random.nextInt() % 100;
            float pressure = random.nextInt() % 100;
            weatherDataObservable.setMeasurements(temperature, humidity, pressure);
            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 10) {
                weatherDataObservable.deleteObserver(currentConditionsDisplayObserver);
            }
        }
    }
}
