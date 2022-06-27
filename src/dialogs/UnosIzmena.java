package dialogs;

import model.Zaposleni;
import persistance.ZaposleniCRUD;

import javax.swing.*;
import java.awt.event.*;

public class UnosIzmena extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField imeTF;
    private JTextField visinaDohotkaTF;
    private JTextField adresaTF;
    private JTextField brojGodinaTF;
    private Zaposleni zaposleni;

    public UnosIzmena() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
    }

    public UnosIzmena(Zaposleni zaposleni) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.zaposleni = zaposleni;

        imeTF.setText(zaposleni.getIme());
        adresaTF.setText(zaposleni.getAdresa());
        brojGodinaTF.setText(String.valueOf(zaposleni.getBroj_godina()));
        visinaDohotkaTF.setText(String.valueOf(zaposleni.getVisina_dohotka()));

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
    }

    private void onOK() {
            if (zaposleni==null) {
                zaposleni = new Zaposleni();
                zaposleni.setIme(this.imeTF.getText());
                zaposleni.setAdresa(this.adresaTF.getText());
                zaposleni.setBroj_godina(Integer.parseInt(this.brojGodinaTF.getText()));
                zaposleni.setVisina_dohotka(Double.parseDouble(this.visinaDohotkaTF.getText()));
                if(ZaposleniCRUD.unosZaposlenog(zaposleni)){
                    Uspesno uspesno = new Uspesno();
                    uspesno.getUspesnoLBL().setText("Uspesan unos");
                    uspesno.setSize(400, 200);
                    uspesno.setVisible(true);
                }
            }
            else {
                zaposleni.setIme(this.imeTF.getText());
                zaposleni.setAdresa(this.adresaTF.getText());
                zaposleni.setBroj_godina(Integer.parseInt(this.brojGodinaTF.getText()));
                zaposleni.setVisina_dohotka(Double.parseDouble(this.visinaDohotkaTF.getText()));
                if(ZaposleniCRUD.izmenaZaposlenog(zaposleni)){
                    Uspesno uspesno = new Uspesno();
                    uspesno.getUspesnoLBL().setText("Uspesna izmena");
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
