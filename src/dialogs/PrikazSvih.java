package dialogs;

import model.Zaposleni;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class PrikazSvih extends JDialog {
    private JPanel contentPane;
    private JButton buttonBack;

    public JTextPane getPrikazSvihTP() {
        return prikazSvihTP;
    }

    private JTextPane prikazSvihTP;

    public PrikazSvih(List<Zaposleni> listaZaposlenih) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonBack);

        StringBuilder sviZaposleni = new StringBuilder();

        for (Zaposleni z : listaZaposlenih) {
            sviZaposleni.append(z);
        }

        prikazSvihTP.setText(sviZaposleni.toString());
        prikazSvihTP.setFocusable(false);

        buttonBack.addActionListener(e -> onBack());

    }

    private void onBack() {
        // add your code here
        dispose();
    }
}
