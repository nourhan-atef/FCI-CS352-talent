package com.FCI.SWE.Controller;
<<<<<<< HEAD
=======

>>>>>>> 531298b31204a14398cc27ecdf1a91c6e3fb4e55
public  class ChatObserver implements Observer{

	Subject sub;
	public String observerName;
	ChatObserver(String observerName)
	{
		this.observerName=observerName;
	}
	
	@Override
	public void addMessage() {
		// TODO Auto-generated method stub
		System.out.print("hoda ");
	}

	@Override
	public void setsubject(Subject s) {
		// TODO Auto-generated method stub
		this.sub=s;
		
	}
	

}
