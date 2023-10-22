import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener, DocumentListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem newFileItem, openItem, saveItem, closeItem, printItem, cutItem, copyItem, pasteItem,
            findItem, selectAllItem, replaceItem, findNextItem, findPreviousItem, undoItem, redoItem;
    private JButton saveAndSubmitButton;
    private UndoManager undoManager;
    private boolean isTextChanged;

    public TextEditor() {
        setTitle("Text Editor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        newFileItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        printItem = new JMenuItem("Print");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");

        findItem = new JMenuItem("Find");
        selectAllItem = new JMenuItem("Select All");
        replaceItem = new JMenuItem("Replace");
        findNextItem = new JMenuItem("Find Next");
        findPreviousItem = new JMenuItem("Find Previous");
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        saveAndSubmitButton = new JButton("Save and Submit");
        cutItem = new JMenuItem(new DefaultEditorKit.CutAction());
        copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        newFileItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);
        printItem.addActionListener(this);
        findItem.addActionListener(this);
        selectAllItem.addActionListener(this);
        replaceItem.addActionListener(this);
        findNextItem.addActionListener(this);
        findPreviousItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        saveAndSubmitButton.addActionListener(this);
        textArea.getDocument().addDocumentListener(this);

        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);

        fileMenu.add(newFileItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        fileMenu.add(printItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(findItem);
        editMenu.add(selectAllItem);
        editMenu.add(replaceItem);
        editMenu.add(findNextItem);
        editMenu.add(findPreviousItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        add(saveAndSubmitButton, BorderLayout.SOUTH);

        setVisible(true);
        isTextChanged = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFileItem) {
            textArea.setText("");
            isTextChanged = false;
        } else if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    reader.close();
                    textArea.setText(content.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                    isTextChanged = false;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == closeItem) {
            textArea.setText("");
            isTextChanged = false;
        } else if (e.getSource() == printItem) {
            try {
                textArea.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == findItem) {
            String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
            if (searchText != null && !searchText.isEmpty()) {
                String content = textArea.getText();
                int startIndex = content.indexOf(searchText);
                if (startIndex != -1) {
                    textArea.setSelectionStart(startIndex);
                    textArea.setSelectionEnd(startIndex + searchText.length());
                } else {
                    JOptionPane.showMessageDialog(this, "Text not found.");
                }
            }
        } else if (e.getSource() == selectAllItem) {
            textArea.selectAll();
        } else if (e.getSource() == replaceItem) {
            String searchText = JOptionPane.showInputDialog(this, "Enter text to replace:");
            if (searchText != null && !searchText.isEmpty()) {
                String replaceText = JOptionPane.showInputDialog(this, "Enter replacement text:");
                if (replaceText != null) {
                    String content = textArea.getText();
                    String replacedContent = content.replace(searchText, replaceText);
                    textArea.setText(replacedContent);
                }
            }
        } else if (e.getSource() == findNextItem) {
            String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
            if (searchText != null && !searchText.isEmpty()) {
                String content = textArea.getText();
                int startIndex = textArea.getSelectionEnd();
                int nextIndex = content.indexOf(searchText, startIndex);
                if (nextIndex != -1) {
                    textArea.setSelectionStart(nextIndex);
                    textArea.setSelectionEnd(nextIndex + searchText.length());
                } else {
                    JOptionPane.showMessageDialog(this, "No more occurrences found.");
                }
            }
        } else if (e.getSource() == findPreviousItem) {
            String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
            if (searchText != null && !searchText.isEmpty()) {
                String content = textArea.getText();
                int startIndex = textArea.getSelectionStart() - searchText.length() - 1;
                int previousIndex = content.lastIndexOf(searchText, startIndex);
                if (previousIndex != -1) {
                    textArea.setSelectionStart(previousIndex);
                    textArea.setSelectionEnd(previousIndex + searchText.length());
                } else {
                    JOptionPane.showMessageDialog(this, "No more occurrences found.");
                }
            }
        } else if (e.getSource() == undoItem) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } else if (e.getSource() == redoItem) {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } else if (e.getSource() == saveAndSubmitButton && isTextChanged) {
            System.out.println("Save and Submit button clicked");
            isTextChanged = false;
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isTextChanged = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isTextChanged = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isTextChanged = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}