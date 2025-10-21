package package1; 

public class MainApp{
    public static void main(String[] args){
        KoneksiDB.initialize();
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
           
                new TransaksiForm().setVisible(true);
            }
        });
    }
}