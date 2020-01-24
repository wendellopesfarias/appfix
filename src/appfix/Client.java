package appfix;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import appfix.model.ExecutionReportBean;
import appfix.tools.Inspector;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.FileStoreFactory;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Initiator;
import quickfix.InvalidMessage;
import quickfix.LogFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RejectLogon;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.UnsupportedMessageType;

@ManagedBean(name = "clientView")
@SessionScoped
public class Client implements Application {

	private static volatile SessionID sessionID;

	private String msg;
	private ExecutionReportBean execution;
	private Map<String,String> mapExecType;
	private Map<String,String> mapOrdStatus;
	private Map<String,String> mapSide;
	
	public Client() {
		execution = new ExecutionReportBean();
		
		mapExecType = Inspector.getFieldsAndValues("ExecType");
		mapOrdStatus = Inspector.getFieldsAndValues("OrdStatus");
		mapSide = Inspector.getFieldsAndValues("Side");
		
	}

	@Override
	public void onCreate(SessionID sessionID) {
		System.out.println("OnCreate");
	}

	@Override
	public void onLogon(SessionID sessionID) {
		System.out.println("OnLogon");
		Client.sessionID = sessionID;
	}

	@Override
	public void onLogout(SessionID sessionID) {
		System.out.println("OnLogout");
		Client.sessionID = null;
	}

	@Override
	public void toAdmin(Message message, SessionID sessionID) {
		System.out.println("ToAdmin");
	}

	@Override
	public void fromAdmin(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		System.out.println("FromAdmin");
	}

	@Override
	public void toApp(Message message, SessionID sessionID) throws DoNotSend {
		System.out.println("ToApp: " + message);
	}

	@Override
	public void fromApp(Message message, SessionID sessionID)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		System.out.println("FromApp");
	}

	public void start()
			throws ConfigError, FileNotFoundException, InterruptedException, InvalidMessage, SessionNotFound {
		
		System.out.println("Start Client.... ");

		SessionSettings settings = new SessionSettings(new FileInputStream("C:\\Users\\wlopes\\eclipse-workspace\\appfix\\Files\\Config\\initiator.properties"));

		Application application = new Client();
		MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
		LogFactory logFactory = new ScreenLogFactory(true, true, true);
		MessageFactory messageFactory = new DefaultMessageFactory();

		Initiator initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory,
				messageFactory);
		initiator.start();

		System.out.println("Finished Start Client.... ");

	}

	public void send() throws UnsupportedEncodingException {
		
		String s = new String(this.msg.toString());

		Message message = null;
		try {
			
			message = new Message(s);

		} catch (InvalidMessage e) {
			e.printStackTrace();
		}

		try {
		
			if (Session.sendToTarget(message, sessionID)) {
				System.out.println("Mensagem SENT!");
			} else {
				System.out.println("ERRORRRRR !");
			}
		} catch (SessionNotFound e) {
			e.printStackTrace();
		}

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ExecutionReportBean getExecution() {
		return execution;
	}

	public void setExecution(ExecutionReportBean execution) {
		this.execution = execution;
	}

	public Map<String, String> getMapExecType() {
		return mapExecType;
	}

	public Map<String, String> getMapOrdStatus() {
		return mapOrdStatus;
	}

	public Map<String, String> getMapSide() {
		return mapSide;
	}
}