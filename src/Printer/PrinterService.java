package printer;

import javax.print.*;

public class PrinterService {

    public static PrintService[] listPrinters() {
        return PrintServiceLookup.lookupPrintServices(null, null);
    }

    public static void showPrinters() {
        PrintService[] printers = listPrinters();

        for (int i = 0; i < printers.length; i++) {
            System.out.println(i + " - " + printers[i].getName());
        }
    }

    public static PrintService getPrinterByIndex(int index) {
        return listPrinters()[index];
    }
}
public void printText(String content, PrintService printer) {
    try {
        DocFlavor flavor = DocFlavor.STRING.TEXT_PLAIN;

        DocPrintJob job = printer.createPrintJob();
        Doc doc = new SimpleDoc(content, flavor, null);

        job.print(doc, null);

    } catch (Exception e) {
        e.printStackTrace();
    }
}