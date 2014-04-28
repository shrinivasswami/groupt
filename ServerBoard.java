
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

 class ServerBoard extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JTextArea messagesArea;
	private JButton sendButton;
	private JTextField message;
	private JButton startServer;
	private SocketMainServer mServer;
	
	public ServerBoard()
	{

		super("ServerBoard -Sam Vin RJ & Rahul");

		JPanel panelFields = new JPanel();
		panelFields.setLayout(new BoxLayout(panelFields, BoxLayout.X_AXIS));

		JPanel panelFields2 = new JPanel();
		panelFields2.setLayout(new BoxLayout(panelFields2, BoxLayout.X_AXIS));

		// here we will have the text messages screen
		messagesArea = new JTextArea();
		messagesArea.setColumns(30);
		messagesArea.setRows(10);
		messagesArea.setEditable(false);
		
		messagesArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			if(arg0.getKeyCode()==KeyEvent.VK_UP)
			{
				String messageText = message.getText();
				messageText = "Server: " + "forward";
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}else if(arg0.getKeyCode()==KeyEvent.VK_DOWN)
			{
				String messageText = message.getText();
				messageText = "Server: " + "back";
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}
			else 	if(arg0.getKeyCode()==KeyEvent.VK_LEFT)
			{
				String messageText = message.getText();
				messageText = "Server: " + "left";
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}
			else 	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				String messageText = message.getText();
				messageText = "Server: " + "right";
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}
			else 	if(arg0.getKeyCode()==KeyEvent.VK_SPACE)
			{
				String messageText = message.getText();
				messageText = "Server: " + "stop";
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}
			System.out.println("Pressed-->");
		
				
			}
			}
		);
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// get the message from the text view
				String messageText = message.getText();
				if(messageText.toString().trim().equals(""))
				{
					message.setText("");
					message.requestFocus();
					return;
				}
				// add message to the message area
				messageText = "Server: " + messageText;
				messagesArea.append("\n"+messageText);
				// send the message to the client
				mServer.sendMessage(messageText);
				// clear text
				message.setText("");
			}
		});

		startServer = new JButton("Start");
		startServer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// disable the start button
				startServer.setEnabled(false);
				messagesArea.append("Server Started, now start Android Client");
				// creates the object OnMessageReceived asked by the DispatcherServer
				// constructor
				mServer = new SocketMainServer(new SocketMainServer.OnMessageReceived()
				{
					@Override
					// this method declared in the interface from DispatcherServer
					// class is implemented here
					// this method is actually a callback method, because it
					// will run every time when it will be called from
					// DispatcherServer class (at while)
					public void messageReceived(String message)
					{
						System.out.println("Msg Recieved");
						messagesArea.append("\n" + message);
					}
				});
				mServer.start();

			}
		});

		// the box where the user enters the text (EditText is called in
		// Android)
		message = new JTextField();
		message.setSize(200, 20);
		message.setScrollOffset(1);

		// add the buttons and the text fields to the panel
		JScrollPane sp = new JScrollPane(messagesArea);
		panelFields.add(sp);
		panelFields.add(startServer);

		//panelFields2.add(message);
		panelFields2.add(sendButton);

		getContentPane().add(panelFields);
		getContentPane().add(panelFields2);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setSize(300, 170);
		setVisible(true);
	}
}
