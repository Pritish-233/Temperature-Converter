package tempconverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple Swing GUI for temperature conversion.
 */
public class ConverterGUI {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 180);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel inputLabel = new JLabel("Value:");
        JTextField inputField = new JTextField(10);

        String[] units = {"C", "F", "K"};
        JComboBox<String> fromBox = new JComboBox<>(units);
        JComboBox<String> toBox = new JComboBox<>(units);

        JButton convertBtn = new JButton("Convert");
        JLabel resultLabel = new JLabel("Result: ");

        gbc.insets = new Insets(6,6,6,6);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(inputLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(inputField, gbc);
        gbc.gridx = 2; gbc.gridy = 0; panel.add(new JLabel("From:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; panel.add(fromBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("To:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(toBox, gbc);
        gbc.gridx = 2; gbc.gridy = 1; panel.add(convertBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; panel.add(resultLabel, gbc);

        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valText = inputField.getText().trim();
                if (valText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a value", "Input needed", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    double v = Double.parseDouble(valText);
                    String from = (String) fromBox.getSelectedItem();
                    String to = (String) toBox.getSelectedItem();
                    double res = TemperatureConverter.convert(v, from, to);
                    resultLabel.setText(String.format("Result: %.4f %s = %.4f %s", v, from, res, to));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(frame, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
