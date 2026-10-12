package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.Scanner;

public class GeneratorePDF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== GENERATORE DI PDF DA CONSOLE ===");
        System.out.print("Inserisci il tuo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Inserisci una frase o un messaggio da inserire nel PDF: ");
        String messaggio = scanner.nextLine();

        String nomeFile = "documento_output.pdf";

        // creazione pdf
        try (PDDocument document = new PDDocument()) {
            // Aggiungiamo una pagina vuota
            PDPage page = new PDPage();
            document.addPage(page);

            // apro flusso di contenuto per scrivere sul pdf
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();

                // imposto stile e grandezza del font
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);

                // posizione del testo sulla pagina con x,y partendo da in basso e destra
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("PDF generato per: " + nome);

                // vado a capo (ammetto di aver usato big gemini per questo prof, senno non stampava il messaggio <3 )
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(0, -30); // Spostamento verso il basso
                contentStream.showText("Messaggio: " + messaggio);

                contentStream.endText();
            }

            // salva il pdf nella cartella del progetto
            document.save(nomeFile);
            System.out.println("\n[SUCCESS] PDF generato con successo: " + nomeFile);

        } catch (IOException e) {
            System.err.println("Errore durante la creazione del PDF: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}