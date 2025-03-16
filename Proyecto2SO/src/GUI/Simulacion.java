/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import EDD.ArbolNario;
import EDD.ListaDoble;
import EDD.NodoArbol;
import EDD.NodoDoble;
import OBJECTS.Archivo;
import OBJECTS.Bloque;
import OBJECTS.SistemaArchivos;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author cristiandresgp
 */
public class Simulacion extends javax.swing.JFrame {
    
    private DefaultTreeModel treeModel;
    private SistemaArchivos sistemaArchivos;
    private DefaultMutableTreeNode root;
    private boolean esAdministrador = false; 
    private JButton[] bloquesBotones;


    /**
     * Creates new form Simulacion
     */
    public Simulacion() {
    this.sistemaArchivos = new SistemaArchivos(100);  // 🔥 Asegúrate de darle un número válido de bloques

    this.root = new DefaultMutableTreeNode(sistemaArchivos.getEstructuraArchivos().getRaiz());
    this.treeModel = new DefaultTreeModel(root);


    initComponents();
    configurarModo(); 
    treeSistema.setModel(treeModel);

    construirJTree();
//    panelMemoria.setLayout(new GridLayout(10, 10, 2, 2));
    inicializarPanelMemoria();
    actualizarVistaSD();

    treeSistema.addTreeSelectionListener(e -> actualizarEstadoBotones());
    treeSistema.setSelectionPath(new TreePath(root.getPath()));

    treeSistema.setCellRenderer(new DefaultTreeCellRenderer() {
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean selected, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus) {
            Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            
            if (node.getUserObject() instanceof NodoArbol) {
                NodoArbol nodo = (NodoArbol) node.getUserObject();
                if (nodo.isDirectorio()) {
                    setIcon(UIManager.getIcon("FileView.directoryIcon")); // Ícono de carpeta
                } else {
                    setIcon(UIManager.getIcon("FileView.fileIcon")); // Ícono de archivo
                }
            }
            return c;
        }
    });
}

public void inicializarPanelMemoria() {
    panelMemoria.removeAll(); // Limpiar el panel antes de agregar nuevos botones
    panelMemoria.setLayout(new GridLayout(10, 10, 2, 2)); // Diseño de rejilla
    
    int totalBloques = sistemaArchivos.getTotalBloques();
    bloquesBotones = new JButton[totalBloques];

    for (int i = 0; i < totalBloques; i++) {
        bloquesBotones[i] = new JButton(); // Inicializar botón
        bloquesBotones[i].setPreferredSize(new Dimension(20, 20)); // Tamaño fijo
        bloquesBotones[i].setOpaque(true); // Asegurar que el color de fondo se aplique
        bloquesBotones[i].setBorderPainted(false); // Opcional: quitar bordes
        bloquesBotones[i].setBackground(Color.GREEN); // Establecer color verde
        panelMemoria.add(bloquesBotones[i]); // Agregar al panel
    }

    panelMemoria.revalidate(); // Actualizar UI
    panelMemoria.repaint(); // Redibujar panel
    actualizarVistaSD(); // Actualizar vista si es necesario
}


public void actualizarVistaSD() {
    SwingUtilities.invokeLater(() -> { // Forzar actualización en el hilo de UI
        for (int i = 0; i < bloquesBotones.length; i++) {
            if (sistemaArchivos.estaBloqueOcupado(i)) {
                bloquesBotones[i].setBackground(Color.RED); // 🔴 Bloque ocupado
                bloquesBotones[i].setText("X"); // Marcar con una "X" los ocupados
            } else {
                bloquesBotones[i].setBackground(Color.GREEN); // 🟢 Bloque libre
                bloquesBotones[i].setText(""); // Vaciar texto en bloques libres
            }
        }
        panelMemoria.revalidate();
        panelMemoria.repaint();
    });
}

    

    
    private void configurarModo() {
    // 🔥 Asegurarnos de que "modo" ya fue creado por initComponents()
    if (modo == null) {
        System.out.println("⚠️ Error: JComboBox 'modo' no ha sido inicializado.");
        return;
    }

    // 🔹 Configurar opciones correctas
    modo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Usuario", "Administrador"}));

    // 🔹 Agregar evento de cambio de modo
    modo.addActionListener(evt -> cambiarModo());

    System.out.println("✅ JComboBox 'modo' configurado correctamente.");
}






    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeSistema = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAsignacion = new javax.swing.JTable();
        panelMemoria = new javax.swing.JPanel();
        btnCrearDirectorio = new javax.swing.JButton();
        btnCrearArchivo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelNombreElemento = new javax.swing.JLabel();
        lblTipoElemento = new javax.swing.JLabel();
        lblInfoMemoria = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        modo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(treeSistema);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 300, 430));

        tablaAsignacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre del archivo", "Tamaño (bloques)", "Primer bloque asignado"
            }
        ));
        jScrollPane2.setViewportView(tablaAsignacion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 490, 430));

        panelMemoria.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelMemoriaLayout = new javax.swing.GroupLayout(panelMemoria);
        panelMemoria.setLayout(panelMemoriaLayout);
        panelMemoriaLayout.setHorizontalGroup(
            panelMemoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
        panelMemoriaLayout.setVerticalGroup(
            panelMemoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        jPanel1.add(panelMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 50, 370, 400));

        btnCrearDirectorio.setText("📂 Crear Directorio");
        btnCrearDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearDirectorioActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrearDirectorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 150, -1));

        btnCrearArchivo.setText("📄 Crear Archivo");
        btnCrearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearArchivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrearArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 150, -1));

        btnEliminar.setText("❌ Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 150, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("INFORMACIÓN");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setText("Tipo:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setText("Tamaño y bloques asignados:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 20));

        labelNombreElemento.setText("nombre");
        jPanel2.add(labelNombreElemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 120, -1));

        lblTipoElemento.setText("tipo");
        jPanel2.add(lblTipoElemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 140, -1));

        lblInfoMemoria.setText("tamaño y bloques asignados");
        jPanel2.add(lblInfoMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 210, 200));

        jLabel5.setText("PANEL DE MEMORIA");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 20, -1, 20));

        btnGuardar.setText("💾 Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        btnCargar.setText("🔃 Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, -1, -1));

        modo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoActionPerformed(evt);
            }
        });
        jPanel1.add(modo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 150, -1));

        jLabel6.setText("MODO:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        btnActualizar.setText("🔄 Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1553, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearDirectorioActionPerformed
     DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
    if (selectedNode == null) return;

    String nombreDir = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo directorio:");
    if (nombreDir == null || nombreDir.trim().isEmpty()) return;

    if (!(selectedNode.getUserObject() instanceof NodoArbol)) return;

    NodoArbol nodoPadre = (NodoArbol) selectedNode.getUserObject();
    NodoArbol nuevoNodo = new NodoArbol(nombreDir, true);
    nodoPadre.agregarHijo(nuevoNodo);

    // 🔥 Agregar el nodo directamente en el `JTree`
    DefaultMutableTreeNode nuevoNodoVisual = new DefaultMutableTreeNode(nuevoNodo);
    selectedNode.add(nuevoNodoVisual);

    treeModel.reload(selectedNode); // 🔥 Recarga solo la parte modificada del árbol
    treeSistema.setSelectionPath(new TreePath(nuevoNodoVisual.getPath())); // 🔥 Mantiene la selección
    actualizarEstadoBotones(); // 🔥 Asegurar que los botones se actualicen
    }//GEN-LAST:event_btnCrearDirectorioActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
    if (selectedNode == null || selectedNode == root) {
        JOptionPane.showMessageDialog(this, "Selecciona un archivo o directorio válido para eliminar.");
        return;
    }

    NodoArbol nodoAEliminar = (NodoArbol) selectedNode.getUserObject();
    NodoArbol nodoPadre = nodoAEliminar.getPadre();

    if (nodoPadre != null) {
        nodoPadre.eliminarHijo(nodoAEliminar.getNombre());
    }

    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
    if (parentNode != null) {
        parentNode.remove(selectedNode);
    }

    treeModel.reload(parentNode);
    actualizarEstadoBotones();
    actualizarTablaAsignacion(); // 🔥 ACTUALIZA LA TABLA TRAS ELIMINAR UN ARCHIVO
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCrearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearArchivoActionPerformed
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
if (selectedNode == null) return;

String nombreArchivo = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo archivo:");
if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) return;

// 🔥 Pedir tamaño en bloques
int tamañoBloques = 0;
while (true) {
    String input = JOptionPane.showInputDialog(this, "Ingrese el tamaño del archivo en bloques:");
    if (input == null) return; // Usuario canceló

    try {
        tamañoBloques = Integer.parseInt(input);
        if (tamañoBloques > 0) break; // Solo aceptar valores positivos
        JOptionPane.showMessageDialog(this, "El tamaño debe ser un número entero positivo.");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
    }
}

if (!(selectedNode.getUserObject() instanceof NodoArbol)) return;
NodoArbol nodoPadre = (NodoArbol) selectedNode.getUserObject();

// 🔥 Verificar la cantidad de bloques disponibles antes de asignar
int bloquesDisponibles = sistemaArchivos.contarBloquesDisponibles();
System.out.println("ℹ️ Bloques disponibles en la SD: " + bloquesDisponibles);

if (tamañoBloques > bloquesDisponibles) {
    JOptionPane.showMessageDialog(this, "No hay suficientes bloques disponibles en la SD. Bloques libres: " + bloquesDisponibles);
    return;
}

// 🔥 Crear archivo con el tamaño ingresado
Archivo nuevoArchivo = new Archivo(nombreArchivo, tamañoBloques, -1);

// 🔥 Asignar bloques en la SD
if (!sistemaArchivos.asignarBloques(nuevoArchivo)) { 
    JOptionPane.showMessageDialog(this, "No hay suficientes bloques disponibles en la SD.");
    return;
}

// 🔥 Agregar archivo al sistema
NodoArbol nuevoNodo = new NodoArbol(nombreArchivo, false);
nodoPadre.agregarHijo(nuevoNodo);
sistemaArchivos.getTablaAsignacion().insert(nombreArchivo, nuevoArchivo);

System.out.println("Archivo creado y almacenado en la tabla: " + nuevoArchivo.getNombre());

// 🔥 Agregar nodo visual en el JTree
DefaultMutableTreeNode nuevoNodoVisual = new DefaultMutableTreeNode(nuevoNodo);
selectedNode.add(nuevoNodoVisual);

treeModel.reload(selectedNode);
treeSistema.setSelectionPath(new TreePath(nuevoNodoVisual.getPath()));

actualizarEstadoBotones();
actualizarTablaAsignacion(); // 🔥 Ahora sí actualizará la tabla correctamente
    }//GEN-LAST:event_btnCrearArchivoActionPerformed

    private void modoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    if (!esAdministrador) {
        JOptionPane.showMessageDialog(this, "Modo Usuario: No puedes actualizar nombres de archivos.");
        return;
    }

    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
    if (selectedNode == null || !(selectedNode.getUserObject() instanceof NodoArbol)) {
        JOptionPane.showMessageDialog(this, "Selecciona un archivo válido.");
        return;
    }

    NodoArbol nodoSeleccionado = (NodoArbol) selectedNode.getUserObject();
    if (nodoSeleccionado.isDirectorio()) {
        JOptionPane.showMessageDialog(this, "Solo se pueden actualizar nombres de archivos.");
        return;
    }

    String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre del archivo:", nodoSeleccionado.getNombre());
    if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
        return; // El usuario canceló o no ingresó nada
    }

    // 🔥 Renombrar en la estructura del árbol
    nodoSeleccionado.setNombre(nuevoNombre);

    // 🔥 Renombrar en la tabla de asignación (Hashtable)
    ListaDoble<Archivo> listaArchivos = sistemaArchivos.getTablaAsignacion().search(nodoSeleccionado.getNombre());
    Archivo archivo = (listaArchivos != null && listaArchivos.getHead() != null) ? listaArchivos.getHead().getElement() : null;
    if (archivo != null) {
        sistemaArchivos.getTablaAsignacion().delete(nodoSeleccionado.getNombre(), archivo);
        archivo.setNombre(nuevoNombre);
        sistemaArchivos.getTablaAsignacion().insert(nuevoNombre, archivo);
    }

    // 🔥 Actualizar el JTree con el nuevo nombre
    selectedNode.setUserObject(nodoSeleccionado);
    treeModel.reload(selectedNode);

    // 🔥 Actualizar la tabla de asignación
    actualizarTablaAsignacion();

    JOptionPane.showMessageDialog(this, "Archivo renombrado con éxito.");
    }//GEN-LAST:event_btnActualizarActionPerformed

    /**
     * Construye el JTree a partir del sistema de archivos
     */
    
    private void construirJTree() {
    root.removeAllChildren();  // Limpiar nodos viejos

    // 🔥 Construir el árbol usando el sistema real de archivos
    agregarNodosRecursivos(root, sistemaArchivos.getEstructuraArchivos().getRaiz());

    treeModel.reload(); // Recargar visualización
}



    /**
 * Método recursivo para agregar nodos al JTree
 * @param padre Nodo del JTree
 * @param nodo Nodo del ArbolNario
 */
private void agregarNodosRecursivos(DefaultMutableTreeNode padre, NodoArbol nodo) {
    NodoDoble<NodoArbol> actual = nodo.getHijos().getHead(); // Obtener la cabeza de la lista

    while (actual != null) {
        NodoArbol hijo = actual.getElement();
        DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(hijo.getNombre());
        padre.add(nuevoNodo); // Agregar al JTree

        if (hijo.isDirectorio()) {
            agregarNodosRecursivos(nuevoNodo, hijo); // Llamada recursiva para subdirectorios
        }
        actual = actual.getNext(); // Avanzar al siguiente nodo en la lista doble
    }
}


    /**
     * Método para actualizar el JTree cuando se haga un cambio en la estructura de archivos
     */
    public void actualizarJTree() {
        construirJTree();
    }

    /**
     * Crea un directorio en el nodo seleccionado
     */
    private void crearDirectorio() {
        String nombre = JOptionPane.showInputDialog("Nombre del nuevo directorio:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
        if (selectedNode == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un directorio primero.");
            return;
        }

        NodoArbol nodoPadre = sistemaArchivos.getEstructuraArchivos().buscarNodo(selectedNode.toString());
        if (nodoPadre != null && nodoPadre.isDirectorio()) {
            sistemaArchivos.getEstructuraArchivos().agregarNodo(selectedNode.toString(), nombre, true);
            actualizarJTree();
        }
    }

    /**
     * Crea un archivo en el nodo seleccionado
     */
    private void crearArchivo() {
        String nombre = JOptionPane.showInputDialog("Nombre del nuevo archivo:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
        if (selectedNode == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un directorio primero.");
            return;
        }

        NodoArbol nodoPadre = sistemaArchivos.getEstructuraArchivos().buscarNodo(selectedNode.toString());
        if (nodoPadre != null && nodoPadre.isDirectorio()) {
            sistemaArchivos.getEstructuraArchivos().agregarNodo(selectedNode.toString(), nombre, false);
            actualizarJTree();
        }
    }

    /**
     * Elimina un archivo o directorio seleccionado
     */
    private void eliminarElemento() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
        if (selectedNode == null || selectedNode == root) {
            JOptionPane.showMessageDialog(this, "Selecciona un archivo o directorio válido para eliminar.");
            return;
        }

        String ruta = selectedNode.toString();
        if (sistemaArchivos.getEstructuraArchivos().eliminarNodo(ruta)) {
            actualizarJTree();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el elemento.");
        }
    }
private void actualizarTablaAsignacion() {
    DefaultTableModel modelo = (DefaultTableModel) tablaAsignacion.getModel();
    modelo.setRowCount(0); // 🔥 Limpiar la tabla antes de actualizar

    System.out.println("🔄 Actualizando tabla de asignación de archivos...");

    recorrerEstructuraParaTabla(sistemaArchivos.getEstructuraArchivos().getRaiz(), modelo);

    int totalArchivos = modelo.getRowCount();
    System.out.println("✅ Total de archivos en la tabla: " + totalArchivos);

    // 🔥 FORZAR REFRESCO EN LA TABLA
    SwingUtilities.invokeLater(() -> {
        modelo.fireTableDataChanged();
        tablaAsignacion.repaint();
    });
}




/**
 * Método recursivo para recorrer la estructura y llenar la tabla con archivos.
 */
private void recorrerEstructuraParaTabla(NodoArbol nodo, DefaultTableModel modelo) {
    if (nodo == null) return;

    NodoDoble<NodoArbol> actual = nodo.getHijos().getHead();
    while (actual != null) {
        NodoArbol hijo = actual.getElement();

        if (!hijo.isDirectorio()) { // ✅ Solo mostrar archivos
            Archivo archivo = buscarArchivoEnTabla(hijo.getNombre());

            if (archivo != null) {
                System.out.println("📂 Añadiendo archivo a la tabla: " + archivo.getNombre());
                modelo.addRow(new Object[]{archivo.getNombre(), archivo.getTamañoEnBloques(), archivo.getPrimerBloque()});
            } else {
                System.out.println("⚠️ Archivo no encontrado en la tabla de asignación: " + hijo.getNombre());
            }
        } else {
            recorrerEstructuraParaTabla(hijo, modelo); // 🔁 Recursión para subdirectorios
        }

        actual = actual.getNext();
    }
}





/**
 * Busca un archivo en la tabla de asignación (Hashtable).
 */
private Archivo buscarArchivoEnTabla(String nombreArchivo) {
    int index = sistemaArchivos.getTablaAsignacion().hashFunction(nombreArchivo);
    ListaDoble<Archivo> lista = sistemaArchivos.getTablaAsignacion().getHashtable()[index];

    if (lista != null && lista.getHead() != null) {
        NodoDoble<Archivo> actual = lista.getHead();
        while (actual != null) {
            if (actual.getElement().getNombre().equals(nombreArchivo)) {
                System.out.println("✅ Archivo encontrado en la tabla de asignación: " + actual.getElement().getNombre());
                return actual.getElement();  // ✅ Retorna el archivo encontrado
            }
            actual = actual.getNext();
        }
    }

    System.out.println("⚠️ Archivo NO encontrado en la tabla de asignación: " + nombreArchivo);
    return null;
}






/**
 * Método recursivo para agregar todos los archivos a la tabla
 */
private void agregarArchivosATabla(NodoArbol nodo, DefaultTableModel modelo) {
    if (nodo == null) return;

    NodoDoble<NodoArbol> actual = nodo.getHijos().getHead();
    while (actual != null) {
        NodoArbol hijo = actual.getElement();
        if (!hijo.isDirectorio()) { // Solo mostrar archivos
            modelo.addRow(new Object[]{hijo.getNombre(), "Pendiente", "Pendiente"});
        } else {
            agregarArchivosATabla(hijo, modelo); // Llamada recursiva para subdirectorios
        }
        actual = actual.getNext();
    }
}


/**
 * Método para actualizar el estado de los botones según la selección en el JTree.
 */
private void actualizarEstadoBotones() {
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();

    if (selectedNode == null) {
        btnCrearDirectorio.setEnabled(false);
        btnCrearArchivo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
        return;
    }

    if (!(selectedNode.getUserObject() instanceof NodoArbol)) {
        btnCrearDirectorio.setEnabled(false);
        btnCrearArchivo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
        return;
    }

    NodoArbol nodoSeleccionado = (NodoArbol) selectedNode.getUserObject();

    if (esAdministrador) {
        btnCrearDirectorio.setEnabled(nodoSeleccionado.isDirectorio());
        btnCrearArchivo.setEnabled(nodoSeleccionado.isDirectorio());
        btnEliminar.setEnabled(true);
        btnActualizar.setEnabled(!nodoSeleccionado.isDirectorio());
    } else {
        btnCrearDirectorio.setEnabled(false);
        btnCrearArchivo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }
}



private void agregarNodoAlTree(String nombre, boolean esDirectorio) {
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSistema.getLastSelectedPathComponent();
    if (selectedNode == null) return;

    if (!(selectedNode.getUserObject() instanceof NodoArbol)) return;

    NodoArbol nodoPadre = (NodoArbol) selectedNode.getUserObject();
    NodoArbol nuevoNodo = new NodoArbol(nombre, esDirectorio);
    nodoPadre.agregarHijo(nuevoNodo);

    // 🔥 Crear el nodo visual con referencia al nodo lógico
    DefaultMutableTreeNode nuevoNodoVisual = new DefaultMutableTreeNode(nuevoNodo);
    selectedNode.add(nuevoNodoVisual);

    treeModel.reload(selectedNode);
    treeSistema.setSelectionPath(new TreePath(nuevoNodoVisual.getPath())); // 🔥 Mantiene la selección en el nuevo nodo
}

private void cambiarModo() {
    esAdministrador = modo.getSelectedItem().equals("Administrador");
    System.out.println("🔄 Modo cambiado: " + (esAdministrador ? "Administrador" : "Usuario"));
    actualizarEstadoBotones();
}







    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnCrearArchivo;
    private javax.swing.JButton btnCrearDirectorio;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNombreElemento;
    private javax.swing.JLabel lblInfoMemoria;
    private javax.swing.JLabel lblTipoElemento;
    private javax.swing.JComboBox<String> modo;
    private javax.swing.JPanel panelMemoria;
    private javax.swing.JTable tablaAsignacion;
    private javax.swing.JTree treeSistema;
    // End of variables declaration//GEN-END:variables
}
