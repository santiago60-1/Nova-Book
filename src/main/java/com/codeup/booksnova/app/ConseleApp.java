// src/main/java/com/codeup/booksnova/app/DialogApp.java
package com.codeup.booksnova.app;

import com.codeup.booksnova.domain.Book;
import com.codeup.booksnova.domain.Partner;
import com.codeup.booksnova.domain.Loan;
import com.codeup.booksnova.service.BookService;
import com.codeup.booksnova.service.BookServiceImpl;
import com.codeup.booksnova.service.PartnerService;
import com.codeup.booksnova.service.PartnerServiceImpl;
import com.codeup.booksnova.service.LoanService;
import com.codeup.booksnova.service.LoanServiceImpl;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ConseleApp {
    private static final BookService    bookSvc    = new BookServiceImpl();
    private static final PartnerService partnerSvc = new PartnerServiceImpl();
    private static final LoanService    loanSvc    = new LoanServiceImpl();

    public static void main(String[] args) {
        String[] mainOpts = {"Manage Books", "Manage Partners", "Manage Loans", "Exit"};
        while (true) {
            int sel = JOptionPane.showOptionDialog(
                null,
                "Main Menu",
                "BooksNova",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                mainOpts,
                mainOpts[0]
            );
            switch (sel) {
                case 0: booksMenu();    break;
                case 1: partnersMenu(); break;
                case 2: loansMenu();    break;
                case 3: System.exit(0);
                default: continue;
            }
        }
    }

    private static void booksMenu() {
        String[] opts = {"Listar", "Crear", "Editar", "Eliminar", "Volver"};
        int sel = JOptionPane.showOptionDialog(
            null,
            "Books Menu",
            "Books",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opts,
            opts[0]
        );

        try {
            switch (sel) {
                case 0: // Listar
                    List<Book> books = bookSvc.findAll();
                    StringBuilder sb1 = new StringBuilder();
                    books.forEach(b -> sb1.append(b).append("\n"));
                    JOptionPane.showMessageDialog(
                        null,
                        sb1.length() == 0 ? "No hay libros." : sb1.toString()
                    );
                    break;

                case 1: // Crear
                    Book nuevo = new Book(
                        null,
                        JOptionPane.showInputDialog("ISBN:"),
                        JOptionPane.showInputDialog("Title:"),
                        JOptionPane.showInputDialog("Author:"),
                        JOptionPane.showInputDialog("Category:"),
                        Integer.parseInt(JOptionPane.showInputDialog("Total copies:")),
                        new BigDecimal(JOptionPane.showInputDialog("Price:")),
                        JOptionPane.showInputDialog("Status:"),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                    );
                    Book creado = bookSvc.create(nuevo);
                    JOptionPane.showMessageDialog(null, "Creado: " + creado);
                    break;

                case 2: // Editar
                    int editId = Integer.parseInt(
                        JOptionPane.showInputDialog("ID del libro a editar:")
                    );
                    Optional<Book> optB = bookSvc.findById(editId);
                    if (optB.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Libro no encontrado.");
                        break;
                    }
                    Book toEdit = optB.get();
                    toEdit.setIsbn(JOptionPane.showInputDialog("New ISBN:", toEdit.getIsbn()));
                    toEdit.setTitle(JOptionPane.showInputDialog("New Title:", toEdit.getTitle()));
                    toEdit.setAuthor(JOptionPane.showInputDialog("New Author:", toEdit.getAuthor()));
                    toEdit.setCategory(JOptionPane.showInputDialog("New Category:", toEdit.getCategory()));
                    toEdit.setTotalCopies(Integer.parseInt(
                        JOptionPane.showInputDialog("New Total copies:", toEdit.getTotalCopies())
                    ));
                    toEdit.setReferencePrice(new BigDecimal(
                        JOptionPane.showInputDialog("New Price:", toEdit.getReferencePrice())
                    ));
                    toEdit.setStatus(JOptionPane.showInputDialog("New Status:", toEdit.getStatus()));
                    Book actualizado = bookSvc.update(toEdit);
                    JOptionPane.showMessageDialog(null, "Libro actualizado: " + actualizado);
                    break;

                case 3: // Eliminar
                    int delId = Integer.parseInt(
                        JOptionPane.showInputDialog("ID del libro a eliminar:")
                    );
                    bookSvc.delete(delId);
                    JOptionPane.showMessageDialog(null, "Libro eliminado.");
                    break;

                default: // Volver o cancelar
                    return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error en Books Menu:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void partnersMenu() {
        String[] opts = {"Listar", "Crear", "Editar", "Eliminar", "Volver"};
        int sel = JOptionPane.showOptionDialog(
            null,
            "Partners Menu",
            "Partners",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opts,
            opts[0]
        );

        try {
            switch (sel) {
                case 0: // Listar
                    List<Partner> list = partnerSvc.findAll();
                    StringBuilder sb2 = new StringBuilder();
                    list.forEach(p -> sb2.append(p).append("\n"));
                    JOptionPane.showMessageDialog(
                        null,
                        sb2.length() == 0 ? "No hay socios." : sb2.toString()
                    );
                    break;

                case 1: // Crear
                    Partner nuevo = new Partner(
                        null,
                        JOptionPane.showInputDialog("Name:"),
                        JOptionPane.showInputDialog("Status:"),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                    );
                    Partner creado = partnerSvc.create(nuevo);
                    JOptionPane.showMessageDialog(null, "Creado: " + creado);
                    break;

                case 2: // Editar
                    int idE = Integer.parseInt(
                        JOptionPane.showInputDialog("ID del socio a editar:")
                    );
                    Optional<Partner> optP = partnerSvc.findById(idE);
                    if (optP.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                        break;
                    }
                    Partner toEdit = optP.get();
                    toEdit.setName(JOptionPane.showInputDialog("New Name:", toEdit.getName()));
                    toEdit.setStatus(JOptionPane.showInputDialog("New Status:", toEdit.getStatus()));
                    Partner updatedP = partnerSvc.update(toEdit);
                    JOptionPane.showMessageDialog(null, "Socio actualizado: " + updatedP);
                    break;

                case 3: // Eliminar
                    int idD = Integer.parseInt(
                        JOptionPane.showInputDialog("ID del socio a eliminar:")
                    );
                    partnerSvc.delete(idD);
                    JOptionPane.showMessageDialog(null, "Socio eliminado.");
                    break;

                default:
                    return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error en Partners Menu:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void loansMenu() {
        String[] opts = {
            "Listar todos",
            "Emitir préstamo",
            "Devolver préstamo",
            "Listar por socio",
            "Volver"
        };
        int sel = JOptionPane.showOptionDialog(
            null,
            "Loans Menu",
            "Loans",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opts,
            opts[0]
        );

        try {
            switch (sel) {


                case 1: // Emitir préstamo
                    int bookId    = Integer.parseInt(
                        JOptionPane.showInputDialog("Book ID:")
                    );
                    int partnerId = Integer.parseInt(
                        JOptionPane.showInputDialog("Partner ID:")
                    );
                    Loan issued = loanSvc.issueLoan(bookId, partnerId);
                    JOptionPane.showMessageDialog(null, "Emitido: " + issued);
                    break;

                case 2: // Devolver préstamo
                    int retId = Integer.parseInt(
                        JOptionPane.showInputDialog("ID del préstamo a devolver:")
                    );
                    Loan returned = loanSvc.returnLoan(retId);
                    JOptionPane.showMessageDialog(null, "Devuelto: " + returned);
                    break;



                default:
                    return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error en Loans Menu:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}