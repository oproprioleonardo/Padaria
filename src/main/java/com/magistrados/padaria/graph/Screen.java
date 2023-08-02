package com.magistrados.padaria.graph;

import com.magistrados.padaria.models.Cliente;
import com.magistrados.padaria.models.VendasCliente;
import com.magistrados.padaria.services.VendasService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Screen extends JFrame {
    private final VendasService vendasService;
    private JPanel mainPanel;
    private JPanel cnpjPanel;
    private JPanel clientePanel;
    private JPanel dadosPanel;
    private JLabel cnpjLabel;
    private JLabel clienteLabel;
    private JLabel dataCompraLabel;
    private JLabel toneladaLabel;
    private JLabel valorCompraLabel;
    private JButton consultarBtn;
    private JTextField cnpjInput;
    private JTextField clienteInput;
    private JTextField toneladaInput;
    private JTextField valorCompraInput;
    private JList<String> dataCompraList;
    private JList<String> toneladaList;
    private JList<String> valorCompraList;
    private JScrollPane dataCompraScroll;
    private JScrollPane toneladaCompraScroll;
    private JScrollPane valorCompraScroll;
    private DefaultListModel<String> dataCompraListModel;
    private DefaultListModel<String> toneladaCompraListModel;
    private DefaultListModel<String> valorCompraListModel;
    private GroupLayout layout;
    private GroupLayout.SequentialGroup hGroup;
    private GroupLayout.SequentialGroup vGroup;

    public Screen(VendasService vendasService) {
        this.vendasService = vendasService;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        initComponents();
        this.pack();
    }

    public void initComponents() {
        //Instanciando Painéis
        mainPanel = new JPanel(new BorderLayout());
        cnpjPanel = new JPanel(new BorderLayout());
        clientePanel = new JPanel(new BorderLayout());
        dadosPanel = new JPanel(new BorderLayout());

        //Intanciando Labels
        cnpjLabel = new JLabel("CNPJ do Cliente:");
        clienteLabel = new JLabel("Cliente:");
        dataCompraLabel = new JLabel("Data Compra");
        toneladaLabel = new JLabel("Toneladas");
        valorCompraLabel = new JLabel("Valor da Compra");

        //Instanciando Inputs
        cnpjInput = new JTextField(30);
        clienteInput = new JTextField(30);
        toneladaInput = new JTextField(30);
        valorCompraInput = new JTextField(30);

        //Intanciando Botão
        consultarBtn = new JButton();
        consultarBtn.setText("Consultar");
        consultarBtn.addActionListener(e -> {
            if (!cnpjInput.getText().isBlank()) {
                try {
                    final Cliente cliente = vendasService.getCliente(cnpjInput.getText());
                    final List<VendasCliente> vendasCliente = vendasService.getVendasCliente(cliente);
                    limparCampos();
                    cnpjInput.setText(cliente.getCnpj());
                    clienteInput.setText(cliente.getNome());

                    vendasCliente.forEach(vendaCliente -> {
                        dataCompraListModel.addElement(vendaCliente.getData());
                        toneladaCompraListModel.addElement(vendaCliente.getToneladas().toString());
                        valorCompraListModel.addElement(vendaCliente.getValor().toString().replace(".", ","));
                    });

                    toneladaInput.setText("" + vendasCliente.stream().mapToDouble(VendasCliente::getToneladas).sum());
                    valorCompraInput.setText("" + vendasCliente.stream().mapToDouble(VendasCliente::getValor).sum());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    limparCampos();
                }
            }
        });

        //Instanciando Modelos de Lista
        dataCompraListModel = new DefaultListModel<>();
        toneladaCompraListModel = new DefaultListModel<>();
        valorCompraListModel = new DefaultListModel<>();

        //Instanciando os Lists
        dataCompraList = new JList<>(dataCompraListModel);
        toneladaList = new JList<>(toneladaCompraListModel);
        valorCompraList = new JList<>(valorCompraListModel);

        //Instanciando Scrolls
        dataCompraScroll = new JScrollPane(dataCompraList);
        toneladaCompraScroll = new JScrollPane(toneladaList);
        valorCompraScroll = new JScrollPane(valorCompraList);

        //Adicionando elementos nos Painéis
        cnpjPanel.add(cnpjLabel, BorderLayout.WEST);
        cnpjPanel.add(cnpjInput, BorderLayout.CENTER);
        cnpjPanel.add(consultarBtn, BorderLayout.EAST);

        clientePanel.add(clienteLabel, BorderLayout.WEST);
        clientePanel.add(clienteInput, BorderLayout.CENTER);

        //GroupLayout
        // Configuração do GroupLayout
        layout = new GroupLayout(dadosPanel);
        dadosPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        // Configuração das horizontais
        hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(dataCompraLabel)
                .addComponent(dataCompraScroll));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(toneladaLabel)
                .addComponent(toneladaCompraScroll)
                .addComponent(toneladaInput));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(valorCompraLabel)
                .addComponent(valorCompraScroll)
                .addComponent(valorCompraInput));
        layout.setHorizontalGroup(hGroup);

        // Configuração das verticais
        vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(dataCompraLabel)
                .addComponent(toneladaLabel)
                .addComponent(valorCompraLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(dataCompraScroll)
                .addComponent(toneladaCompraScroll)
                .addComponent(valorCompraScroll));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(toneladaInput)
                .addComponent(valorCompraInput));
        layout.setVerticalGroup(vGroup);

        //Adicionando Painéis no JFrame
        mainPanel.add(cnpjPanel, BorderLayout.NORTH);
        mainPanel.add(clientePanel, BorderLayout.CENTER);
        mainPanel.add(dadosPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);


    }

    public void limparCampos() {
        cnpjInput.setText("");
        clienteInput.setText("");

        dataCompraListModel.clear();
        toneladaCompraListModel.clear();
        valorCompraListModel.clear();

        toneladaInput.setText("");
        valorCompraInput.setText("");
    }
}