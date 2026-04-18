package printer;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class PrinterService {

    public static PrintService[] listPrinters() {
        return PrintServiceLookup.lookupPrintServices(null, null);
    }

    public static void showPrinters() {
        PrintService[] printers = listPrinters();

        System.out.println("=== IMPRESSORAS DISPONÍVEIS ===");

        for (int i = 0; i < printers.length; i++) {
            System.out.println(i + " - " + printers[i].getName());
        }
    }

    public static PrintService getPrinterByIndex(int index) {
        PrintService[] printers = listPrinters();

        if (index < 0 || index >= printers.length) {
            throw new IllegalArgumentException("Índice de impressora inválido");
        }

        return printers[index];
    }

    public void printText(String content, PrintService printer) {
        try {
            byte[] bytes = content.getBytes();

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

            DocPrintJob job = printer.createPrintJob();

            Doc doc = new SimpleDoc(new java.io.ByteArrayInputStream(bytes), flavor, null);

            job.print(doc, null);

            System.out.println("Enviado para impressão!");

        } catch (Exception e) {
            System.out.println("Erro ao imprimir:");
            e.printStackTrace();
        }
    }
}