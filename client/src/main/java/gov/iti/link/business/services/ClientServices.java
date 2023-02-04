package gov.iti.link.business.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gov.iti.link.business.DTOs.UserDTO;

public interface ClientServices extends Remote {

    void notifyContactStatus(UserDTO contactDto, boolean isActive ) throws RemoteException;
}
