/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Mapa;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author j4v13
 */
public class Grafico extends JFrame {
    
    private Mapa mapa;
    private JButton btnArchivo;
    
    public Grafico() throws IOException {
        super("Islas");
        super.setSize(450, 450);
        super.setLayout(new BorderLayout());
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        mapa = new Mapa();
        super.add(pnlSuperior(), BorderLayout.NORTH);
        super.setVisible(true);
    }
    
    private JPanel pnlInferior() {
        JPanel pnlInferior = new JPanel();
        pnlInferior.setLayout(new FlowLayout());
        int islas = mapa.numeroIslas();
        JLabel lblIslas = new JLabel("Numero de islas: "+islas);
        pnlInferior.add(lblIslas);
        return pnlInferior;
    }
    
    private JPanel pnlMapa() {
        JPanel pnlMapa = new JPanel();
        int[][] mapita = mapa.getMapa();
        int f = mapita.length;
        int c = mapita[0].length;
        pnlMapa.setLayout(new GridLayout(f, c));
        for (int i = 0; i < f; i++) {                     
            for (int j = 0; j < c; j++) {
                ImageIcon icon;
                JLabel aux = new JLabel();   
                aux.setOpaque(true);
                if(mapita[i][j] == 0){
                    icon = new ImageIcon("oceano.jpg");
                    aux.setIcon(icon);
                } else {
                    icon = new ImageIcon("tierra.jpg");
                    aux.setIcon(icon);
                }
                pnlMapa.add(aux);
            }
        }
        return pnlMapa;
    }
    
    private JPanel pnlSuperior() {
        JPanel pnlSuperior = new JPanel();
        pnlSuperior.setLayout(new FlowLayout());
        btnArchivo = new JButton("Abrir");
        btnArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(Grafico.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        mapa.abrirMapa(fc.getSelectedFile());
                        Grafico.super.add(pnlMapa(), BorderLayout.CENTER);
                        Grafico.super.add(pnlInferior(), BorderLayout.SOUTH);
                        Grafico.super.setVisible(true);
                    } catch (Exception  ex) {
                        JOptionPane.showMessageDialog(pnlSuperior, "Entrada invalida");
                    }
                }
            }
        });
        pnlSuperior.add(btnArchivo);
        return pnlSuperior;
    }
}
