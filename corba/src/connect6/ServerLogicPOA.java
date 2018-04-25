package connect6;


/**
* connect6/ServerLogicPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from connect6.idl
* �����, 25 ������ 2018 �., 2:17:34 ������, ����������� �����
*/

public abstract class ServerLogicPOA extends org.omg.PortableServer.Servant
 implements connect6.ServerLogicOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registerClient", new java.lang.Integer (0));
    _methods.put ("sendMove", new java.lang.Integer (1));
    _methods.put ("receiveMove", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // connect6/ServerLogic/registerClient
       {
         connect6.ClientLogic client = connect6.ClientLogicHelper.read (in);
         this.registerClient (client);
         out = $rh.createReply();
         break;
       }

       case 1:  // connect6/ServerLogic/sendMove
       {
         connect6.ClientLogic client = connect6.ClientLogicHelper.read (in);
         String move = in.read_string ();
         this.sendMove (client, move);
         out = $rh.createReply();
         break;
       }

       case 2:  // connect6/ServerLogic/receiveMove
       {
         String move = in.read_string ();
         this.receiveMove (move);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:connect6/ServerLogic:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ServerLogic _this() 
  {
    return ServerLogicHelper.narrow(
    super._this_object());
  }

  public ServerLogic _this(org.omg.CORBA.ORB orb) 
  {
    return ServerLogicHelper.narrow(
    super._this_object(orb));
  }


} // class ServerLogicPOA
