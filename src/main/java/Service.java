import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Service {
    String text;

    public void textWillBeOpen(JTextArea jTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    jTextArea.append(scanner.nextLine() + "\n");
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void textWillBeSave(JTextArea jTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                PrintWriter printWriter = new PrintWriter(file);
                Scanner scanner = new Scanner(jTextArea.getText());
                while (scanner.hasNextLine()) {
                    printWriter.println(scanner.nextLine() + "\n");
                    printWriter.close();
                }

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void textWillBeSaveAS(JTextArea jTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as");
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                PrintWriter printWriter = new PrintWriter(file);
                Scanner scanner = new Scanner(jTextArea.getText());
                while (scanner.hasNextLine()) {
                    printWriter.println(scanner.nextLine() + "\n");
                    printWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeNotepad(JMenuItem close) throws Exception {
        int message = JOptionPane.showConfirmDialog(close, "Czy napewno chcesz wyjsć z programu?"
                , "Zamknij program", JOptionPane.INFORMATION_MESSAGE);
        if (message == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void infoNotepad(JMenuItem about) {
        JOptionPane.showMessageDialog(about, "Alternatywa dla programu Notatnik \n" +
                        "Wersja demo: v2.0 \n" + " Develop by: Karol Sidor",
                "My Notepad-informacje", JOptionPane.INFORMATION_MESSAGE);
    }

    public void normalWindows(JFrame frame) {
        frame.setSize(850, 500);
        frame.setLocationRelativeTo(null);
    }

    public void fullScreen(JFrame jFrame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int height = dimension.height;
        int width = dimension.width;
        jFrame.setBounds(0, 0, width, height);
    }

    public void textLowerCase(JTextArea jTextArea) {
        String small = jTextArea.getText();
        String lowercase = small.toLowerCase();
        jTextArea.setText(lowercase);
    }

    public void textUpperCase(JTextArea jTextArea) {
        String large = jTextArea.getText();
        String uppercase = large.toUpperCase();
        jTextArea.setText(uppercase);
    }

    public void textWillBeWrappingLines(JTextArea jTextArea) {
        jTextArea.setLineWrap(true);

    }

    public void cutText(JTextArea jTextArea) {
        text = jTextArea.getSelectedText();
        String actualText = jTextArea.getText();
        jTextArea.setText(null);
    }

    public void pasteText(JTextArea jTextArea) {
        jTextArea.insert(text, jTextArea.getCaretPosition());
    }

    public void copyText(JTextArea jTextArea) {
        text = jTextArea.getSelectedText();
    }

    public void setFontSizeText(JTextArea jTextArea, JMenuItem fontSize) {
        try {
            text = jTextArea.getText();
            String size = JOptionPane.showInputDialog(fontSize, "Wprowadź rozmiar czcionki",
                    "Ustawienia rozmiaru czcionki", JOptionPane.INFORMATION_MESSAGE);
            jTextArea.setFont(new Font("Lucida Console", Font.PLAIN, Integer.parseInt(size)));
            jTextArea.setText(text);

        } catch (NumberFormatException e) {
            jTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
        }
    }

    public void newDocument(JMenuItem nowy, JFrame jFrame) {

        int message = JOptionPane.showConfirmDialog(nowy, "Czy napewno chcesz otworzyć nowy dokument ?",
                "Nowy dokument", JOptionPane.INFORMATION_MESSAGE);

        if (message == JOptionPane.YES_OPTION) {
            jFrame.dispose();
            Frame frame = new Frame();

        }
    }

    public void deleteText(JTextArea jTextArea) {
        text = jTextArea.getText();
        jTextArea.setText(null);
    }

    public void insertTime(JTextArea jTextArea) {
        text = jTextArea.getText();
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        jTextArea.setText(text + "\n " + time.toString() + "  " + date.toString());
    }

    public void helperNotepad(JMenuItem helper) {
        JOptionPane.showMessageDialog(helper, "             Przepraszamy !!! \n Pomoc techniczna dla" +
                        " programu \n My Notepad jest w budownie :( ", "Pomoc programu My Notepad",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void joinText(JTextArea jTextArea) {
        text = jTextArea.getSelectedText();
        jTextArea.insert(text + "\n", jTextArea.getCaretPosition());
    }

    public void setStyleText(JDialog jDialog, JLabel label, JButton jButton, JCheckBox normally,
                             JCheckBox italic, JCheckBox bold, JTextArea jTextArea) {

        label = new JLabel("Wybierz rodzaj czcionki");
        jButton = new JButton("Zatwierdź");
        normally = new JCheckBox("Normalna");
        bold = new JCheckBox("Pogrubiona");
        italic = new JCheckBox("Kursywa");

        jDialog = new JDialog();
        jDialog.setLocationByPlatform(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = (int) dimension.getWidth() / 4;
        int height = (int) dimension.getHeight() / 4;
        jDialog.setBounds(width, height, 325, 250);
        jDialog.setTitle("Ustawienia stylu czcionki");
        jDialog.setResizable(false);
        jDialog.setLayout(null);
        jButton.setBounds(100, 150, 140, 35);
        label.setBounds(80, 10, 180, 35);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        normally.setBounds(80, 40, 180, 35);
        bold.setBounds(80, 70, 180, 35);
        italic.setBounds(80, 100, 180, 35);
        jDialog.add(normally);
        jDialog.add(bold);
        jDialog.add(italic);
        jDialog.add(bold);
        jDialog.add(italic);
        jDialog.add(label);
        jDialog.add(jButton);
        JDialogListner(jButton, jDialog, jTextArea, normally, bold, italic);
        jDialog.setVisible(true);
    }

    private void JDialogListner(JButton jButton, JDialog jDialog, JTextArea jTextArea, JCheckBox normally,
                                JCheckBox bold, JCheckBox italic) {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });
        int size = jTextArea.getFont().getSize();
        normally.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextArea.getText();
                jTextArea.setFont(new Font("Lucida Console", Font.PLAIN, size));
                jTextArea.setText(text);
            }
        });
        bold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texty = jTextArea.getText();
                jTextArea.setFont(new Font("Lucida Console", Font.BOLD, size));
                jTextArea.setText(texty);
            }
        });
        italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texts = jTextArea.getText();
                jTextArea.setFont(new Font("Lucida Console", Font.ITALIC, size));
                jTextArea.setText(texts);
            }
        });
    }
}
