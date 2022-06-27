package dialogs;

import model.Zaposleni;
import persistance.ZaposleniCRUD;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class PretragaPo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField pretragaTF;
    private JLabel pretragaLBL;
    private final String opcija;
    private String unos;

    public PretragaPo(String s) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        opcija = s;

        buttonOK.addActionListener(e -> onOK());

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
        if (opcija.equals("ime")) {
            pretragaLBL.setText("Unesite ime");
        }
        else
            pretragaLBL.setText("Unesite id");


    }

    private void onOK() {
        unos = this.pretragaTF.getText();

        if (opcija.equals("ime")){
            List<Zaposleni> listaZaposlenih;
            listaZaposlenih = ZaposleniCRUD.prikazPoImenu(unos);

            StringBuilder sviZaposleni = new StringBuilder();

            for (Zaposleni z : listaZaposlenih) {
                sviZaposleni.append(z);
            }

            PrikazSvih prikazPoImenu = new PrikazSvih(listaZaposlenih);
            prikazPoImenu.setSize(800, 400);
            prikazPoImenu.setVisible(true);
            prikazPoImenu.getPrikazSvihTP().setText(sviZaposleni.toString());
            prikazPoImenu.getPrikazSvihTP().setFocusable(false);

        }
        else if (opcija.equals("izmena")){

            Zaposleni zaposleni = ZaposleniCRUD.pretragaPoId(Integer.parseInt(unos));
            UnosIzmena unosIzmena = new UnosIzmena(zaposleni);
            unosIzmena.setSize(800,400);
            unosIzmena.setVisible(true);

        }
        else {
            if(ZaposleniCRUD.brisanjeZaposlenog(Integer.parseInt(unos))) {
                Uspesno uspesno = new Uspesno();
                uspesno.getUspesnoLBL().setText("dialogs.Uspesno brisanje");
                uspesno.setSize(400, 200);
                uspesno.setVisible(true);
            }
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}