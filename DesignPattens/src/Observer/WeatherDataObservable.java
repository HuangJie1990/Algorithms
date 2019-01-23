package Observer;

import java.util.Observable;
import java.util.Observer;

public class WeatherDataObservable extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherDataObservable() {
    }

    public void measurementChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
