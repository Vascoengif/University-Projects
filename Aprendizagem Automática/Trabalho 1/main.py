import numpy as np 
import random
import math
from pprint import pprint
from DecisionTree import Node

from sklearn.model_selection import train_test_split
# from sklearn.datasets import load_breast_cancer
# from sklearn.metrics import accuracy_score 
# from sklearn.metrics import classification_report 
# from sklearn.metrics import confusion_matrix 
# from sklearn.tree import DecisionTreeClassifier


def classes(ydata):  #retorna um array com as classes existentes e o número de classes existentes
	array_classes = []
	array_classes.append(ydata[0])
	count = 1;
	
	for index in ydata:
		for i in array_classes:
			if i == index:
				break
			if array_classes[-1] == i:
				array_classes.append(index)
				count+=1
	return array_classes


def fancy_classes(xdata, x): #retorna os elementos nao repetidos da coluna pretendente do xdata
	array = []

	for index in xdata:
		array.append(index[x])

	return classes(array)


def nr_N_nr_P(ydata, array):  #retorna quantas vezes cada classe aparece

	array_N_P = []
	j = 0

	for posicao in array:
		array_N_P.append(0)

		for index in ydata:
			if index == posicao:
				array_N_P[j]+=1

		j+=1
	return array_N_P


def probabilidade(array): #calcula a parte da probabilidade
	soma = sum(array)
	index = 0
	for i in array:
		array[index] = (i/soma)
		index+=1
	return array


def entropy(array): #calcula a entropia
    total_entropy = 0
    for i in array:
        if i != 0:
        	total_entropy += -i * math.log(i, 2)
    return total_entropy


def gini(array): #calcula a gini
	total_gini = 0
	for i in array:
		total_gini += i**2
	return 1 - total_gini


def taxa_erro(array): #calcula a Taxa de erro
	taxa_erro = 0
	for i in array:
		taxa_erro += i/sum(array)
	return 1 - taxa_erro


def impureza_atributo(impurezas, ydata, count): #calcula a impureza de cada atributo
	resultado = 0
	i = 0
	for index in impurezas:
		resultado += (sum(count[i]))/(len(ydata)) * index
		i+=1
		
	return resultado
	

def Gain(xdata, ydata, array_classes, impureza, arg_impureza): #calcula o gain e coloca todos num array
	nr_atributos = len(xdata[0])
	x = 0
	impurezas = []
	gain = []
	data_geral = []

	while x < nr_atributos:
		impurezas.append([])
		aux = fancy_classes(xdata, x)
		wright_counts = []
		j = 0
		index1 = ydata[j]
		y = 0
		l = 0

		while y < len(aux):
			count = []
			wright_counts.append([])
			
			for t in array_classes:
				count.append(0)
				wright_counts[y].append(0)

			for index in xdata:
				if index[x] == aux[y] and index1 == ydata[j]:
					for k in array_classes:
						if k == ydata[j]:
							count[l] += 1
							wright_counts[y][l] += 1
						l += 1
					l =0

				if j == len(ydata)-1:
					break
				j+=1
				index1 = ydata[j]

			if len(count) == 2:
				for k in count:
					if k == 0:
						metodo = 0
						break
					elif count[-1] == k:
						if arg_impureza == 1:
							metodo = entropy(probabilidade(count))
						elif arg_impureza == 2:
							metodo = gini(probabilidade(count))
						elif arg_impureza == 3:
							metodo = taxa_erro(probabilidade(count))
			else:
				if arg_impureza == 1:
					metodo = entropy(probabilidade(count))
				elif arg_impureza == 2:
					metodo = gini(probabilidade(count))
				elif arg_impureza == 3:
					metodo = taxa_erro(probabilidade(count))

			impurezas[x].append(metodo)
			y+=1
			j=0

		gain.append(impureza - impureza_atributo(impurezas[x], ydata, wright_counts))
		
		x+=1

	return gain, impurezas


def auxiliar_validation_funtion(index_i, index_k, zdata):
	validation = []
	for zlinha_validacao in zdata:
		index_j = 0
		index_i += 1
		aux_validation = []
		validation.append(validation_funtion(index_i, index_j, index_k, zdata, aux_validation))
	return validation


def validation_funtion(i, j, k, zdata, array):

	if zdata[j-1] == zdata[-1] and j != 0:
		return array
		
	elif zdata[i] != zdata[j]:
		if zdata[i][k] == zdata[i][-1]:
			return array
		elif zdata[i][k] == zdata[j][k]:
			if zdata[i][-1] == zdata[j][-1]:
				array.append(True)
			else:
				array.append(False)
	j += 1

	return validation_funtion(i, j, k, zdata, array)
			

def percorrer_validacao(array):
	aux = True
	for x in array:
		for i in x:
			if i == False:
				aux = False
			else:
				aux = True
				break
		if aux == False:
			return False
	return True


def fit(xdata, ydata, atributos):
	i = 0
	zdata = []
	zdata.append([])
	for zlinha in xdata:
		for zposicao in zlinha:

			zdata[i].append(zposicao)
                                              #criação do zdata(xdata+ydata)
			if len(zlinha) == len(zdata[i]):
				zdata[i].append(ydata[i])
				if len(xdata) != len(zdata):
					zdata.append([])
		i += 1

	# atributos = data[0,0:-1]
	print("\nescolha um numero: \n1 - entropia \n2 - gini \n3 - taxa de erro")
	arg_impureza = int(input())

	return fit_aux(xdata, ydata, zdata, atributos, arg_impureza)


def fit_aux(xdata, ydata, zdata, atributos, arg_impureza): #constroi a arvore
	array = []
	higher = 0
	lower = 1000
	array = classes(ydata)

	switch_impureza = {
		1: entropy(probabilidade(nr_N_nr_P (ydata, array))),	#funciona para todos mas no ficheiro 4 apenas 7/20
		2: gini(probabilidade(nr_N_nr_P (ydata, array))),		#so funciona para o ficheiro 1
		3: taxa_erro(probabilidade(nr_N_nr_P (ydata, array)))	#so nao funciona para o ficheiro 4
	}

	impureza = switch_impureza.get(arg_impureza)
	gain_total, impureza_total = Gain(xdata, ydata, array, impureza, arg_impureza)

	i = 0
	auxi = 0
	for high in gain_total:
		if high > higher:
			higher = high
			auxi = i
		i += 1
	
	n = Node(atributos[auxi])

	filhos = fancy_classes(xdata, auxi)

	for x in filhos:
		n.insert(Node(x))
		
	j=0

	for low in impureza_total[auxi]:
		if low == 0:
			for z in zdata:
				if z[auxi] == filhos[j]:
					n.inser(j, Node(filhos[j]), z[-1])
					break
					
		else:
			new_zdata = []
			new_xdata = []
			new_ydata = []
			r = -1

			for dados in zdata:
				if dados[auxi] == filhos[j]:
					new_zdata.append(dados)
					new_ydata.append(dados[-1])

			for g in new_zdata:
				new_xdata.append([])
				r+=1
				
				for v in g:
					if g[-1] != v:
						new_xdata[r].append(v)

			if new_zdata != []:

				array_verificacao = []
				index_i = -1
				index_k = 0
				while True:
					if auxi != index_k:
						array_verificacao.append(auxiliar_validation_funtion(index_i, index_k, zdata))

					if (len(new_xdata[0])-1) == len(array_verificacao):
						break
					
					index_k += 1


				for true in array_verificacao:
					serValido = percorrer_validacao(true)
				
					if serValido == True:
						n.inserir(j, fit_aux(new_xdata, new_ydata, new_zdata, atributos, arg_impureza))
						break


				if serValido == False:
					valores = nr_N_nr_P(ydata, array)
					incrementacao = 0
					for something in array:
						if valores[incrementacao] == max(valores):
							n.inser(j, Node(filhos[j]), something)
							break
						incrementacao+=1
		j+=1

	return n

	
def Percorrer_Arvore(x_test, y_test, atributos, raiz): #indica qual a linha que vai testar na matriz de teste
	
	answer = []
	for teste in x_test:
		answer.append(Percorrer_Arvore_Auxiliar(x_test, y_test, atributos, raiz, teste))

	return answer == y_test #verifica cada posicao do array das respostas obtidas e igual a cada posicao do array das respostas corretas
							#retorna um bool para cada posicao

				
def Percorrer_Arvore_Auxiliar(x_test, y_test, atributos, raiz, teste): #percorre a arvore verificando os nos e os ramos correspondentes ao que recebe da linha teste
	i=0
	for x in atributos:
		if raiz.leaf():
			return raiz.data
		
		elif x == raiz.data:
			for filhos in raiz.kids:
				if filhos.arco == teste[i]:
					return Percorrer_Arvore_Auxiliar(x_test, y_test, atributos, filhos, teste)
		
		elif "" == raiz.arco:
			return Percorrer_Arvore_Auxiliar(x_test, y_test, atributos, raiz.kids[0], teste)
			
		i += 1

def score_aux(x_train, y_train, atributos, raiz):

	answer = Percorrer_Arvore(x_train, y_train, atributos, raiz) 
	respostas = classes(answer)
	print(answer)
	calculo_erro = nr_N_nr_P(answer, respostas)
	return score(respostas, calculo_erro)

def score(respostas, calculo_erro): #calcula a probabilidade de acertar
	i = 0
	fim = 0
	for x in respostas:
		if x == True:
			fim = (calculo_erro[i]/sum(calculo_erro))*100
		i += 1

	return fim


def pruning(x_train, y_train, atributos, raiz):

	#resultado_1 = score_aux(x_train, y_train, atributos, raiz)

	pruning_aux(x_train, y_train, atributos, raiz)

	#resultado_2 = score_aux(x_train, y_train, atributos, raiz)
	

def pruning_aux(x_train, y_train, atributos, raiz):

	booleano, filho = raiz.kidsLeaf()

	if booleano:
		array = classes(y_train)
		valores = nr_N_nr_P(y_train, array)
		incrementacao = 0
		for maximo in array:
			if valores[incrementacao] == max(valores):
				raiz.substituicao(maximo)
				break
			incrementacao+=1
		return

	else:
		return pruning_aux(x_train, y_train, atributos, filho)


def Data_Split(data): #divide os dados
	
	xdata = data[1:,0:-1] # dados: da segunda à ultima linha, da primeira à penúltima coluna
	ydata = data[1:,-1] # classe: da segunda à ultima linha, só última coluna
	atributos = data[0,0:-1]

	x_train, x_test, y_train, y_test = train_test_split(xdata, ydata, test_size=0.25)

	return xdata, ydata, x_train, x_test, y_train, y_test, atributos

###################################################################  MAIN  ##################################################################################################
switch_ficheiro = {
    1: "weather.nominal.csv",
    2: "vote.csv",
    3: "contact-lenses.csv",
    4: "soybean.csv"  
}

while True:
	print("\nescolha um numero: \n1 - weather.nominal.csv \n2 - vote.csv \n3 - contact-lenses.csv \n4 - soybean.csv \n0 - Exit")
	arg_ficheiro = int(input())
	if arg_ficheiro != 0:
		data = np.genfromtxt(switch_ficheiro.get(arg_ficheiro), delimiter=",", dtype=None, encoding=None)
		xdata, ydata, x_train, x_test, y_train, y_test, atributos = Data_Split(data)
		raiz = Node("")
		raiz.insert(fit(x_train, y_train, atributos)) #cria uma arvore consoante os dados de treino
		raiz.printArvore()
		print()
		print("\nPruning? y/n")
		arg_pruning = input()
		print()
		
		if arg_pruning == 'y':
			pruning(x_train, y_train, atributos, raiz)
			raiz.printArvore()
			print()

		print("score:", score_aux(x_test, y_test, atributos, raiz), "%")

	else:
		break