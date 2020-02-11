'''
Created on Mar 13, 2014

@author: emanuel
'''

# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
# Apk android
apk = '../bin/ponto-android.apk'
# Seta o pacote da aplicacao
package = 'com.fernandomantoan.ponto.android'
# Seta a activity que sera startada
activity = '.activity.MainActivity'
# Seta o que sera startado
runComponent = package + '/' + activity


#Conecta ao device, starta a activity e retorna a activity
def startActivity():
	print("O aplicativo esta sendo instalado")
	# Conecta ao device
	device = MonkeyRunner.waitForConnection()
	# Instala o App
	device.installPackage(apk)
	# Starta a activity
	device.startActivity(component=runComponent)
	# Dorme 5 segundos
	MonkeyRunner.sleep(5)
	# Retorna o device
	return device

	