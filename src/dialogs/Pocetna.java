package dialogs;

import model.Zaposleni;
import persistance.ZaposleniCRUD;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Pocetna extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton brisanjeButton;
    private JButton izmenaButton;
    private JButton unosButton;
    private JButton prikazPoImenuButton;
    private JButton prikazSvihButton;

    public Pocetna() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        prikazSvihButton.addActionListener(e -> {
            List<Zaposleni> listaZaposlenih = ZaposleniCRUD.prikazSvihZaposlenih();
                PrikazSvih prikazSvih = new PrikazSvih(listaZaposlenih);
                prikazSvih.setSize(800, 400);
                prikazSvih.setVisible(true);

        });
        unosButton.addActionListener(e -> {
            UnosIzmena unosIzmena = new UnosIzmena();
            unosIzmena.setSize(800,400);
            unosIzmena.setVisible(true);
        });

        prikazPoImenuButton.addActionListener(e -> {
            PretragaPo pretragaPo = new PretragaPo("ime");
            pretragaPo.setSize(800,400);
            pretragaPo.setVisible(true);
        });

        izmenaButton.addActionListener(e -> {
            PretragaPo pretragaPo = new PretragaPo("izmena");
            pretragaPo.setSize(800,400);
            pretragaPo.setVisible(true);
        });

        brisanjeButton.addActionListener(e -> {
            PretragaPo pretragaPo = new PretragaPo("brisanje");
            pretragaPo.setSize(800,400);
            pretragaPo.setVisible(true);
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        System.exit(0);
    }
}
