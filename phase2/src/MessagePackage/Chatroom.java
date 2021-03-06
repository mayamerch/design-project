package MessagePackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Chatroom implements Conversation, Serializable {

    private ArrayList<Integer> userList;
    private ArrayList<Message> messages;
    private int senderID;

    /**
     * Creates a new chatroom between two or more users in userList
     * @param userList a list of all users one can message
     * @param senderID the ID of the user sending this message
     */
    public Chatroom(int senderID, ArrayList<Integer> userList){
        this.userList = userList;
        this.senderID = senderID;
        this.messages = new ArrayList<Message>();
    }

    /**
     * Constructor used in Gateway to instantiate an existing chatroom with all of its data
     * @param userList a list of all users one can message
     * @param messages an ArrayList of Messages in the chatroom
     */
    public Chatroom(ArrayList<Integer> userList, ArrayList<Message> messages){
        this.userList = userList;
        this.messages = messages;
    }

    public ArrayList<Integer> getUserList() {
        return userList;
    }

    public int getSenderID() {
        return senderID;
    }

    @Override
    public void sendMessage(String messageStr, int senderUserID){
        //Message newMessage = new Message(messageStr, senderUserID);
        //this.messageQueue.pushMessage(newMessage);
        this.messages.add(new Message(messageStr, senderUserID));
    }

    @Override
    public ArrayList<Message> readMessages(){
        //return this.messageQueue.getMessages();
        return this.messages;
    }

    /**
     * @return the ArrayList of userIDs who are being sent a message
     */
    @Override
    public ArrayList<Integer> getAllReaderIDs(){
        return this.userList;
    }

    /**
     * @return the ArrayList of userIDs who are sending the message
     */
    @Override
    public ArrayList<Integer> getAllSenderIDs(){
        return this.userList;
    }

    /**
     * @return the ArrayList of userIDs who can read this message
     */
    @Override
    public boolean canRead(Integer userID){
        return this.getAllReaderIDs().contains(userID);
    }

    /**
     * @return the ArrayList of userIDs who can send a message
     */
    @Override
    public boolean canSend(Integer userID){
        return this.getAllSenderIDs().contains(userID);
    }


    /**
     * To use in Gateway class for saving Chatroom as a string to write to file.
     * @return string of form userList+\n+messageQueue
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        for(Message m : this.messages){
            s.append(m.toString()).append("\t");
        }
        String str = s.toString().trim() + "]";
        return (this.userList + "\n" + str);
    }

    /**
     * To use to make a string for printing to the console.
     * @return String formatted for printing to console for textUI.
     */
    public String format(){
        //return this.messageQueue.format();
        StringBuilder s = new StringBuilder();
        for(Message m : this.messages){
            s.append(m.format());
            s.append("\n");
        }
        return s.toString();
    }



}
