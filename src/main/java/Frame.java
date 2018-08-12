import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame implements ActionListener {


    private JMenuItem nowy, open, save, saveAs, close, cut, copy, paste, fullscreen, normal, about;
    private String text;
    private JTextArea jTextArea;
    private JMenu file, edit, format, view, help;
    private JMenuItem lowerCase, upperCase, wrappingLines, fontSize, delete, time, helper, style;
    private JButton jButton;
    private JCheckBox normally, bold, italic;
    private JLabel label;
    private JMenuItem jcut, jcopy, jpaste, jdelete, join;
    private Object source;
    private JMenuBar menuBar;
    private ScrollPane scrollPane;
    private JDialog jDialog;
    private JPopupMenu popupMenu;
    private Service service;

    Frame() {
        createFrame();
        createComponent();
        createKeybordShortCut();
        createMenu();
        createPopupMenu();
        actionListner();
        setVisible(true);
    }

    private void createFrame() {
        setSize(850, 500);
        setLocationRelativeTo(null);
        setTitle(" Bez tytułu - My Notepad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image image = new ImageIcon("src/main/resources/ikona.jpg").getImage();
        setIconImage(image);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void createComponent() {
        scrollPane = new ScrollPane();
        jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
        menuBar = new JMenuBar();
        file = new JMenu("Plik");
        edit = new JMenu("Edycja");
        format = new JMenu("Format");
        view = new JMenu("Widok");
        help = new JMenu("Pomoc");
        nowy = new JMenuItem("Nowy");
        open = new JMenuItem("Otwórz...");
        save = new JMenuItem("Zapisz");
        saveAs = new JMenuItem("Zapisz jako...");
        close = new JMenuItem("Zakończ");
        cut = new JMenuItem("Wytnij");
        copy = new JMenuItem("Kopiuj");
        paste = new JMenuItem("Wklej");
        delete = new JMenuItem("Usuń");
        time = new JMenuItem("Godzina/data");
        lowerCase = new JMenuItem("Małe litery");
        upperCase = new JMenuItem("Wielkie litery");
        wrappingLines = new JMenuItem("Zawijanie wierszy");
        fontSize = new JMenuItem("Rozmiar czcionki");
        style = new JMenuItem("Styl czcionki");
        fullscreen = new JMenuItem("Pełen ekran");
        normal = new JMenuItem("Widok standardowy");
        helper = new JMenuItem("Wyświetl pomoc");
        about = new JMenuItem("My Notepad-informacje");
    }

    private void createKeybordShortCut() {

        nowy.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        cut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        copy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        paste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        delete.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
        fullscreen.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
        time.setAccelerator(KeyStroke.getKeyStroke("F5"));
    }

    private void createMenu() {
        setJMenuBar(menuBar);
        menuBar.add(file);
        file.add(nowy);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(close);
        menuBar.add(edit);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.addSeparator();
        edit.add(time);
        menuBar.add(format);
        format.add(lowerCase);
        format.add(upperCase);
        format.add(wrappingLines);
        format.addSeparator();
        format.add(fontSize);
        format.add(style);
        menuBar.add(view);
        view.add(normal);
        view.add(fullscreen);
        menuBar.add(help);
        help.add(helper);
        help.addSeparator();
        help.add(about);
        scrollPane.add(jTextArea);
        add(scrollPane);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        jcut = new JMenuItem("Wytnij");
        jcopy = new JMenuItem("Kopiuj");
        jpaste = new JMenuItem("Wklej");
        jdelete = new JMenuItem("Usuń wszystko");
        join = new JMenuItem("Dołącz");

        popupMenu.add(jcut);
        popupMenu.add(jcopy);
        popupMenu.add(jpaste);
        popupMenu.add(join);
        popupMenu.addSeparator();
        popupMenu.add(jdelete);

        jTextArea.setComponentPopupMenu(popupMenu);

    }

    private void actionListner() {
        nowy.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        fullscreen.addActionListener(this);
        normal.addActionListener(this);
        lowerCase.addActionListener(this);
        upperCase.addActionListener(this);
        wrappingLines.addActionListener(this);
        fontSize.addActionListener(this);
        style.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        time.addActionListener(this);
        about.addActionListener(this);
        helper.addActionListener(this);
        close.addActionListener(this);
        jcut.addActionListener(this);
        jpaste.addActionListener(this);
        jcopy.addActionListener(this);
        jdelete.addActionListener(this);
        join.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        source = e.getSource();
        service = new Service();

        if (source == open) {
            service.textWillBeOpen(jTextArea);
        }
        if (source == save) {
            service.textWillBeSave(jTextArea);
        }
        if (source == saveAs) {
            service.textWillBeSaveAS(jTextArea);
        }
        if (source == close) {
            try {
                service.closeNotepad(close);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (source == about) {
            service.infoNotepad(about);
        }
        if (source == normal) {
            service.normalWindows(this);
        }
        if (source == fullscreen) {
            service.fullScreen(this);
        }
        if (source == lowerCase) {
            service.textLowerCase(jTextArea);
        }
        if (source == upperCase) {
            service.textUpperCase(jTextArea);
        }
        if (source == wrappingLines) {
            service.textWillBeWrappingLines(jTextArea);
            wrappingLines.setFont(new Font(null, Font.BOLD, 12));
        }
        if (source == cut) {
            service.cutText(jTextArea);
        }
        if (source == paste) {
            service.pasteText(jTextArea);
        }
        if (source == copy) {
            service.copyText(jTextArea);
        }
        if (source == fontSize) {
            service.setFontSizeText(jTextArea, fontSize);
        }
        if (source == nowy) {
            service.newDocument(nowy, this);
        }
        if (source == delete) {
            service.deleteText(jTextArea);
        }
        if (source == time) {
            service.insertTime(jTextArea);
        }
        if (source == helper) {
            service.helperNotepad(helper);
        }
        if (source == style) {
            service.setStyleText(jDialog, label, jButton, normally, italic, bold, jTextArea);
        }
        if (source == jcut) {
            service.cutText(jTextArea);
        }
        if (source == jcopy) {
            service.copyText(jTextArea);
        }
        if (source == jpaste) {
            service.pasteText(jTextArea);
        }
        if (source == jdelete) {
            service.deleteText(jTextArea);
        }
        if (source == join) {
            service.joinText(jTextArea);
        }
    }
}




