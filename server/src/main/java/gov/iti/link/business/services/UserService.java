package gov.iti.link.business.services;

import gov.iti.link.business.DTOs.ContactDto;
import gov.iti.link.business.DTOs.GroupDto;
import gov.iti.link.business.DTOs.InvitationDTO;
import gov.iti.link.business.DTOs.UserDTO;
import gov.iti.link.persistence.entities.ContactEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

public interface UserService extends Remote {
    UserDTO save(UserDTO user) throws RemoteException;

    UserDTO findByPhone(String phone) throws RemoteException;

    Vector<UserDTO> getAllUsers() throws RemoteException;

    InvitationDTO sendInvite(String fromPhone, String toPhone) throws RemoteException;

    int addContact(String userPhone, String friendPhone) throws RemoteException;

    Vector<ContactDto> getAllContacts(String userPhone) throws RemoteException;
    int updateUser(UserDTO user) throws RemoteException;


    List<InvitationDTO> getInvitations(String userPhone) throws RemoteException;
    void acceptInvite(InvitationDTO invite) throws RemoteException;
    void rejectInvite(InvitationDTO invite) throws RemoteException;

    void userLoggedIn(ClientServices clientServices,UserDTO userDTO) throws RemoteException;
    void userLoggedOut(ClientServices clientServices,UserDTO userDTO) throws RemoteException;

    void sendMessage(String fromPhone,String message,Vector<String>toPhone) throws RemoteException;

    GroupDto createGroup(String groupName) throws RemoteException;
    //GroupDto getGroup(int id) throws RemoteException;
    int addMemberToGroup(GroupDto groupDto,String memberPhone) throws RemoteException;
    Vector<GroupDto> getAllGroups(String mamberPhone) throws RemoteException;
    Vector<String> getAllGroupMembers(int id) throws RemoteException;


  
}
