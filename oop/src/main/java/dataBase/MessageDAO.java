package dataBase;
import objects.messages.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDAO implements MessageDAOInterface{
    BasicDataSource ds;
    public MessageDAO(BasicDataSource basicDataSource){
        this.ds = basicDataSource;
    }

    @Override
    public boolean addMessage(Message message) {
        try {
            Connection conn = ds.getConnection();
            PreparedStatement stm;

            int senderID = message.getSenderID();
            int recieverID = message.getRecieverID();
            if (senderID == -1 || recieverID == -1)
                return false;
            String statement = "INSERT INTO MESSAGE (Sender_ID, Reciever_ID, Message_type, Message_content,Date_sent) VALUE (?, ?, ?, ?,?) ;";
            stm = conn.prepareStatement(statement, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, senderID);
            stm.setInt(2, recieverID);
            stm.setString(3, message.getType());
            stm.setString(4, message.getContent());
            stm.setTimestamp(5,message.getDate());
            int added = stm.executeUpdate();
            if (added != 1) return false;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                message.setId(rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteMessage(int id){
        try{
    Connection conn = ds.getConnection();
    PreparedStatement stm = conn.prepareStatement("DELETE FROM MESSAGE WHERE ID = ? ;");
    stm.setInt(1,id);
    int changed = stm.executeUpdate();
    if(changed == 1) return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteNotifications(int reciever_id){
        try{
            Connection conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM MESSAGE WHERE Reciever_ID = ? AND Message_type != 'NOTE' ;");
            stm.setInt(1,reciever_id);
            int changed = stm.executeUpdate();
            if(changed > 0) return changed;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteAllMessages(int sender_id, int reciever_id){
        if(sender_id == -1 || reciever_id == -1)
            return -1;
        try{
            Connection conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM MESSAGE WHERE ((Sender_ID = ? AND Reciever_ID = ?) OR (Sender_ID = ? AND Reciever_ID = ?) ) AND Message_type = ? ;");
            stm.setInt(1,sender_id);
            stm.setInt(2,reciever_id);
            stm.setInt(3,reciever_id);
            stm.setInt(4,sender_id);
            stm.setString(5,"NOTE");
            int changed = stm.executeUpdate();
            if(changed > 0) return changed;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Message getMessage(int id){
        Message res = null;
        try{
            Connection conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM MESSAGE WHERE ID = ? ;");
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) res = messageParser(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    private Message messageParser(ResultSet rs) {
        try {
            int id = rs.getInt(1);
            int sender_id = rs.getInt(2);
            int reciever_id = rs.getInt(3);
            String type = rs.getString(4);
            if(type.equals("NOTE")){
                return new NoteMessage(id,sender_id,reciever_id,rs.getString(5),rs.getTimestamp(6));
            } else if (type.equals("REQUEST")){
                return new RequestMessage(id,sender_id,reciever_id,rs.getTimestamp(6));
            }else if (type.equals("CHALLENGE")){
                return new ChallengeMessage(id,sender_id,reciever_id,rs.getString(5),rs.getTimestamp(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
            }
        return null;
    }

    @Override
    public List<Message> getChat(int sender_id, int reciever_id, boolean asc){
        if(sender_id == -1 || reciever_id == -1)
            return null;
        List<Message> result = new ArrayList<>();
        try{
            Connection conn = ds.getConnection();
            PreparedStatement stm;
            String statement =  "SELECT * FROM MESSAGE WHERE ((Sender_ID = ? AND Reciever_ID = ?) OR (Sender_ID = ? AND Reciever_ID = ?)) AND Message_type = ? ORDER BY Date_sent "+ (asc ? "asc" : "desc") +";";
            stm = conn.prepareStatement(statement);
            stm.setInt(1,sender_id);
            stm.setInt(2,reciever_id);
            stm.setInt(3,reciever_id);
            stm.setInt(4,sender_id);
            stm.setString(5,"NOTE");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result.add(new NoteMessage(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(5),rs.getTimestamp(6)));
            }
            if(!result.isEmpty())Collections.sort(result);
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

        @Override
    public List<Message> getUsersRecentIncomingNotifications(int user_id){
        List<Message> result = new ArrayList<>();
        try {
            Connection conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM MESSAGE WHERE Reciever_ID = ? AND Message_type != 'NOTE' ORDER BY Date_sent DESC ;");
            stm.setInt(1,user_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                result.add(messageParser(rs));
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getInteractions(int user_id) {
        List<Integer> result = new ArrayList<>();
        try {
            Connection conn = ds.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT Sender_ID FROM MESSAGE WHERE Reciever_ID = ? AND Message_type = 'NOTE';");
            stm.setInt(1,user_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                if(!result.contains(rs.getInt(1))) result.add(rs.getInt(1));
            }

            PreparedStatement stm2 = conn.prepareStatement("SELECT Reciever_ID FROM MESSAGE WHERE Sender_ID = ? AND Message_type = 'NOTE';");
            stm2.setInt(1,user_id);
            ResultSet rs2 = stm.executeQuery();
            while(rs2.next()){
                if(!result.contains(rs2.getInt(1))) result.add(rs2.getInt(1));

            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}
