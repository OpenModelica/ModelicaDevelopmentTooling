package org.modelica.mdt.omc.internal.corba;

/**
* OmcCommunicationOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from omc_communication.idl
* Thursday, October 27, 2005 10:11:20 AM CEST
*/


// As simple as can be omc communication, sending and recieving of strings.
public interface OmcCommunicationOperations 
{
  String sendExpression (String expr);
  String sendClass (String model);
} // interface OmcCommunicationOperations
