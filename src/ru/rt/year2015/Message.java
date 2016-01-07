package ru.rt.year2015;

import org.dom4j.Element;

public class Message {
    String SName;
    String SCode;
    String RName;
    String RCode;
    String OName;
    String OCode;

    public Message(String SendName, String Sendcode, String RecName, String RecCode, String OrName, String OrCode) {
        SName = SendName;
        SCode = Sendcode;
        RName = RecName;
        RCode = RecCode;
        OName = OrName;
        OCode = OrCode;
    };

    public void CreateMessage (Element root) {
        Element Message = root.addElement("smev:Message");
        Message.addAttribute("xmlns:smev","http://smev.gosuslugi.ru/rev120315");

        Element Sender = Message.addElement("smev:Sender");
        Element Code = Sender.addElement("smev:Code")
                .addText(SCode);
        Element Name = Sender.addElement("smev:Name")
                .addText(SName);
        Element Recipient = Message.addElement("smev:Recipient");
        Code = Recipient.addElement("smev:Code")
                .addText(RCode);
        Name = Recipient.addElement("smev:Name")
                .addText(RName);

        Element Originator = Message.addElement("smev:Originator");
        Code = Originator.addElement("smev:Code")
                .addText(OCode);
        Name = Originator.addElement("smev:Name")
                .addText(OName);

        Element TypeCode = Message.addElement("smev:TypeCode")
                .addText("GSRV");
        Element Status = Message.addElement("smev:Status")
                .addText("REQUEST");
        Element Date = Message.addElement("smev:Date")
                .addText("2000-01-01T00:00:00");
        Element ExchangeType = Message.addElement("smev:ExchangeType")
                .addText("3");
        Element ServiceCode = Message.addElement("smev:ServiceCode")
                .addText("5440100010000545045");
        Element CaseNumber = Message.addElement("smev:CaseNumber")
                .addText("70263950");
    }
}