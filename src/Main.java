import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JTextField cantidadField;
    private JComboBox<String> monedaOrigenCombo;
    private JComboBox<String> monedaDestinoCombo;
    private CurrencyConverter converter; // Declarar la variable converter

    public Main() {
        JFrame frame = new JFrame("Conversor de Monedas");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadField = new JTextField();
        JLabel monedaOrigenLabel = new JLabel("Desde:");
        monedaOrigenCombo = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "GBP","MXN","AED",	"AFN",	"ALL",	"AMD",	"ANG",	"AOA",	"ARS",	"AUD",	"AWG",	"AZN",	"BAM",	"BBD",	"BDT",	"BGN",	"BHD",	"BIF",	"BMD",	"BND",	"BOB",	"BRL",	"BSD",	"BTN",	"BWP",	"BYN",	"BZD",	"CAD",	"CDF",	"CHF",	"CLP",	"CNY",	"COP",	"CRC",	"CUP",	"CVE",	"CZK",	"DJF",	"DKK",	"DOP",	"DZD",	"EGP",	"ERN",	"ETB",	"FJD",	"FKP",	"FOK",	"GEL",	"GGP",	"GHS",	"GIP",	"GMD",	"GNF",	"GTQ",	"GYD",	"HKD",	"HNL",	"HRK",	"HTG",	"HUF",	"IDR",	"ILS",	"IMP",	"INR",	"IQD",	"IRR",	"ISK",	"JEP",	"JMD",	"JOD",	"KES",	"KGS",	"KHR",	"KID",	"KMF",	"KRW",	"KWD",	"KYD",	"KZT",	"LAK",	"LBP",	"LKR",	"LRD",	"LSL",	"LYD",	"MAD",	"MDL",	"MGA",	"MKD",	"MMK",	"MNT",	"MOP",	"MRU",	"MUR",	"MVR",	"MWK",	"MYR",	"MZN",	"NAD",	"NGN",	"NIO",	"NOK",	"NPR",	"NZD",	"OMR",	"PAB",	"PEN",	"PGK",	"PHP",	"PKR",	"PLN",	"PYG",	"QAR",	"RON",	"RSD",	"RUB",	"RWF",	"SAR",	"SBD",	"SCR",	"SDG",	"SEK",	"SGD",	"SHP",	"SLE",	"SLL",	"SOS",	"SRD",	"SSP",	"STN",	"SYP",	"SZL",	"THB",	"TJS",	"TMT",	"TND",	"TOP",	"TRY",	"TTD",	"TVD",	"TWD",	"TZS",	"UAH",	"UGX",	"UYU",	"UZS",	"VES",	"VND",	"VUV",	"WST",	"XAF",	"XCD",	"XDR",	"XOF",	"XPF",	"YER",	"ZAR",	"ZMW",	"ZWL",
        }); //
        JLabel monedaDestinoLabel = new JLabel("Hacia:");
        monedaDestinoCombo = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "GBP","MXN","AED",	"AFN",	"ALL",	"AMD",	"ANG",	"AOA",	"ARS",	"AUD",	"AWG",	"AZN",	"BAM",	"BBD",	"BDT",	"BGN",	"BHD",	"BIF",	"BMD",	"BND",	"BOB",	"BRL",	"BSD",	"BTN",	"BWP",	"BYN",	"BZD",	"CAD",	"CDF",	"CHF",	"CLP",	"CNY",	"COP",	"CRC",	"CUP",	"CVE",	"CZK",	"DJF",	"DKK",	"DOP",	"DZD",	"EGP",	"ERN",	"ETB",	"FJD",	"FKP",	"FOK",	"GEL",	"GGP",	"GHS",	"GIP",	"GMD",	"GNF",	"GTQ",	"GYD",	"HKD",	"HNL",	"HRK",	"HTG",	"HUF",	"IDR",	"ILS",	"IMP",	"INR",	"IQD",	"IRR",	"ISK",	"JEP",	"JMD",	"JOD",	"KES",	"KGS",	"KHR",	"KID",	"KMF",	"KRW",	"KWD",	"KYD",	"KZT",	"LAK",	"LBP",	"LKR",	"LRD",	"LSL",	"LYD",	"MAD",	"MDL",	"MGA",	"MKD",	"MMK",	"MNT",	"MOP",	"MRU",	"MUR",	"MVR",	"MWK",	"MYR",	"MZN",	"NAD",	"NGN",	"NIO",	"NOK",	"NPR",	"NZD",	"OMR",	"PAB",	"PEN",	"PGK",	"PHP",	"PKR",	"PLN",	"PYG",	"QAR",	"RON",	"RSD",	"RUB",	"RWF",	"SAR",	"SBD",	"SCR",	"SDG",	"SEK",	"SGD",	"SHP",	"SLE",	"SLL",	"SOS",	"SRD",	"SSP",	"STN",	"SYP",	"SZL",	"THB",	"TJS",	"TMT",	"TND",	"TOP",	"TRY",	"TTD",	"TVD",	"TWD",	"TZS",	"UAH",	"UGX",	"UYU",	"UZS",	"VES",	"VND",	"VUV",	"WST",	"XAF",	"XCD",	"XDR",	"XOF",	"XPF",	"YER",	"ZAR",	"ZMW",	"ZWL",
        }); //
        JButton convertirButton = new JButton("Convertir");

        converter = new CurrencyConverter(); // Inicializar el converter aquí

        convertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cantidadStr = cantidadField.getText();
                String monedaOrigen = (String) monedaOrigenCombo.getSelectedItem();
                String monedaDestino = (String) monedaDestinoCombo.getSelectedItem();

                // Validar y procesar la conversión aquí
                if (!cantidadStr.isEmpty()) {
                    double cantidad = Double.parseDouble(cantidadStr);
                    try {
                        double resultado = converter.convertCurrency(monedaOrigen, monedaDestino, cantidad);
                        JOptionPane.showMessageDialog(frame,
                                cantidad + " " + monedaOrigen + " equivale a " + resultado + " " + monedaDestino,
                                "Resultado de la Conversión",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Error al convertir la moneda: " + ex.getMessage(),
                                "Error de Conversión",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Por favor ingrese una cantidad válida.",
                            "Error de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(cantidadLabel);
        panel.add(cantidadField);
        panel.add(monedaOrigenLabel);
        panel.add(monedaOrigenCombo);
        panel.add(monedaDestinoLabel);
        panel.add(monedaDestinoCombo);
        panel.add(new JLabel()); // Espacio en blanco para alinear el botón
        panel.add(convertirButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
