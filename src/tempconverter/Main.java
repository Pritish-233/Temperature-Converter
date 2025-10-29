package tempconverter;

import java.util.Scanner;

/**
 * Main - simple CLI for TemperatureConverter.
 *
 * Usage:
 *  - Run without arguments for CLI mode.
 *  - Run with argument "gui" to launch Swing GUI: java -cp src tempconverter.Main gui
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("gui")) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                ConverterGUI.createAndShowGUI();
            });
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("=== Temperature Converter (CLI) ===");
        System.out.println("Supported units: C (Celsius), F (Fahrenheit), K (Kelvin)");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            try {
                System.out.print("Enter value (or 'exit'): ");
                String valStr = sc.nextLine().trim();
                if (valStr.equalsIgnoreCase("exit")) break;
                double value = Double.parseDouble(valStr);

                System.out.print("From unit (C/F/K): ");
                String from = sc.nextLine().trim();
                if (from.equalsIgnoreCase("exit")) break;

                System.out.print("To unit (C/F/K): ");
                String to = sc.nextLine().trim();
                if (to.equalsIgnoreCase("exit")) break;

                double result = TemperatureConverter.convert(value, from, to);
                System.out.printf("%.4f %s = %.4f %s%n%n", value, from.toUpperCase(), result, to.toUpperCase());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number. Try again.\n");
            } catch (IllegalArgumentException iae) {
                System.out.println("Error: " + iae.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Goodbye!");
        sc.close();
    }
}
