package tempconverter;

/**
 * TemperatureConverter
 * Static methods to convert between Celsius, Fahrenheit and Kelvin.
 */
public class TemperatureConverter {

    // C -> F
    public static double celsiusToFahrenheit(double c) {
        return c * 9.0 / 5.0 + 32.0;
    }

    // C -> K
    public static double celsiusToKelvin(double c) {
        return c + 273.15;
    }

    // F -> C
    public static double fahrenheitToCelsius(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    // F -> K
    public static double fahrenheitToKelvin(double f) {
        return fahrenheitToCelsius(f) + 273.15;
    }

    // K -> C
    public static double kelvinToCelsius(double k) {
        return k - 273.15;
    }

    // K -> F
    public static double kelvinToFahrenheit(double k) {
        return celsiusToFahrenheit(kelvinToCelsius(k));
    }

    /**
     * Generic conversion method. Units are "C", "F", "K" (case-insensitive).
     * Throws IllegalArgumentException for invalid unit.
     */
    public static double convert(double value, String fromUnit, String toUnit) {
        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        String f = fromUnit.trim().toUpperCase();
        String t = toUnit.trim().toUpperCase();

        if (f.equals(t)) return value;

        switch (f) {
            case "C":
                switch (t) {
                    case "F": return celsiusToFahrenheit(value);
                    case "K": return celsiusToKelvin(value);
                }
                break;
            case "F":
                switch (t) {
                    case "C": return fahrenheitToCelsius(value);
                    case "K": return fahrenheitToKelvin(value);
                }
                break;
            case "K":
                switch (t) {
                    case "C": return kelvinToCelsius(value);
                    case "F": return kelvinToFahrenheit(value);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid from-unit: " + fromUnit);
        }
        throw new IllegalArgumentException("Invalid to-unit: " + toUnit);
    }
}
