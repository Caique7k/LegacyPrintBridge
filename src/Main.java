import printer.PrinterService;
import watcher.FileWatcher;

import javax.print.PrintService;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        PrinterService.showPrinters();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Escolha a impressora: ");
        int index = scanner.nextInt();

        PrintService selectedPrinter = PrinterService.getPrinterByIndex(index);

        System.out.println("Usando: " + selectedPrinter.getName());

        FileWatcher watcher = new FileWatcher();

        watcher.watch("C:\\print_capture", file -> {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));

                new PrinterService().printText(content, selectedPrinter);

                file.delete();

                System.out.println("Impresso: " + file.getName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}