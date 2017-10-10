#coding: utf-8

from sys import argv
from threading import Thread, Condition
from random import *
from tkinter import *
import time


class File():
	def __init__(self):
		self.file = []
		self.Cond = Condition()

	def deposer(self,prod):
		with self.Cond :
			b = randint(0,100)
			prod.label["text"] = "Producteur : Ajout de l'entier " + str(b) + " dans la file"
			while(len(self.file)>20):
				prod.label["text"] = "Producteur : File pleine. En attente d'une place pour l'entier : " + str(b) 
				self.Cond.wait()
			self.file.append(b)
			label["text"] = "File : " + str(self.file)
			self.Cond.notifyAll()

	def retirer(self,consom):
		with self.Cond :
			while(len(self.file)==0):
				consom.label["text"] = "Consommateurs "+ str(consom.n)+" : File vide. En attente de l'ajout d'un entier"
				self.Cond.wait()
			consom.label["text"] = "Consommateurs "+str(consom.n)+" : Retrait de l'entier " + str(self.file[0]) + " dans la file"
			r = self.file[0]
			self.file.remove(self.file[0])
			consom.label["text"] = "Consommateurs "+str(consom.n)+" : Retrait de " + str(r) + " effectué"
			label["text"] = "File : " + str(self.file)
			self.Cond.notifyAll()


class Producteurs(Thread):
	def __init__(self, temps, file):
		Thread.__init__(self)
		self.temps = int(temps)
		self.file = file
		self.label = Label(fenetre, text="")
		self.label.pack()
		self.daemon = True

	def run(self):
		while(1):
			self.file.deposer(self)
			time.sleep(self.temps)
				

class Consommateurs(Thread):
	def __init__(self, temps, file, n):
		Thread.__init__(self)
		self.temps = int(temps)
		self.file = file
		self.n = n
		self.label = Label(fenetre, text="")
		self.label.pack()
		self.daemon = True

	def run(self):
		while(1):
			self.file.retirer(self)
			time.sleep(self.temps)



def lancement():
	F = File()
	Prod = Producteurs(1,F)
	Consom1 = Consommateurs(4,F,1)
	Consom2 = Consommateurs(4,F,2)
	bouton_Commencer.pack_forget()
	bouton_quitter = Button(fenetre, text="Quitter", command=fermeture)
	bouton_quitter.pack()
	Prod.start()
	Consom1.start()
	Consom2.start()

def fermeture():
	fenetre.destroy()


fenetre = Tk()
fenetre.title("Exercice 3.1")
fenetre.geometry("%dx%d%+d%+d" % (600,200,(fenetre.winfo_screenwidth()-600)/2,(fenetre.winfo_screenheight()-200)/2) )
label = Label(fenetre, text="File :")
label.pack()
bouton_Commencer = Button(fenetre, text="Commencer", command=lancement)
bouton_Commencer.pack()
fenetre.mainloop()


