#include <windows.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <stdio.h>
#include "STB_image.h"
#include <iostream>

int janela_ativa = 1; // 0 - tela inicial , 1 - creditos
int ativa_animacao = 1; //0 - animacao ativada , 1 - animacao desativada
int escolhaVisualizacao = 0; // 0-Tela Inicial, 1-Sistema Solar, 2-Mercurio, 3-Venus, 4-Terra, 5-Marte,  6-Jupiter, 7-Saturno, 8-Urano , 9-Netuno, 10-Tela Final
static int rotY_sol, rotY_mercury, rotY_venus, rotY, rotY_marte, rotY_jupiter, rotY_saturno, rotY_urano, rotY_netuno;
static int rotX, obsZ, year = 0, day = 0;
int posicaoluz = 0;
float angulo_mecury=0.0, angulo_venus = 0.0, angulo = 0.0 , angulo_marte = 0.0 , angulo_jupiter = 0.0, angulo_saturno = 0.0, angulo_urano = 0.0, angulo_netuno = 0.0;
GLfloat Angulo, Aspecto, Larg_Janela, Alt_Janela;
GLuint sol, mercury ,venus, terra, marte, jupiter, saturno, urano, netuno, espaco, logoUERN;
GLuint Velocidade=2;
float  posicao_espectador_z = 10.0, posicao_espaco_x = 35.0, posicao_espaco_y = 15.0, posicao_texto_x = 0.0, posicao_texto_y = 0.0;
float  raio_sol = 2.91/2, raio_mercury = 0.01/2, raio_venus = 0.025/2, raio_terra = 0.027/2, raio_marte = 0.014/2 , raio_jupiter = 0.3/2, raio_saturno = 0.25/2, raio_urano =  0.107/2, raio_netuno = 0.103/2;

GLfloat VelocidadeTranslacao = 0.1;

/* Cria vetores para controle de luzes na cena */
GLfloat ambiente[] = { 0.2, 0.2, 0.2, 1.0 };
GLfloat difusa[] = { 0.7, 0.7, 0.7, 1.0 };
GLfloat especular[] = { 1.0, 1.0, 1.0, 1.0 };
GLfloat posicao[] = { 0.0, 3.0, 2.0, 0.0 };
GLfloat lmodelo_ambiente[] = { 0.2, 0.2, 0.2, 1.0 };
GLfloat semespecular[4]={0.0,0.0,0.0,1.0};

//--------------------------------------------------------------
void Timer(int value);
//void Anima_Idle(void);
void Iluminar();
//void PosicionaObservador(void);
void Inicializar(void);
void Esfera(float raio,int longitude,int latitude);
void Desenhar(void);
void UsarTeclado(unsigned char key, int x, int y);
//void UsarMouse(int button, int state, int x, int y);
void AjustarJanela(GLsizei w, GLsizei h);
void CriaMenu();	

void TelaInicial();
void verSistemaSolar();
void verMercury();
void verVenus();
void verTerra();
void verMarte();
void verJupiter();
void verSaturno();
void verUrano();
void verNetuno();
//--------------------------------------------------------------


// Função callback chamada quando o tamanho da janela é alterado 
void AjustarJanela(GLsizei w, GLsizei h)
{  Larg_Janela=w;
   Alt_Janela=h;
   glViewport(0, 0, (GLsizei)w, (GLsizei)h);
   Aspecto = (GLfloat)w / (GLfloat)h;
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();
   gluPerspective(60.0, Aspecto, 1.0, 30.0);
   glMatrixMode(GL_MODELVIEW);
   glLoadIdentity();
   gluLookAt(0.0, 0.0, posicao_espectador_z, 0.0, 0.0, 0.0, 0.0, 1, 0.0);
  }

void Timer(int value)
{   

	//rotação
	rotY_sol = (rotY_sol + 1 * Velocidade / 2) % 360;
	rotY_mercury = (rotY_mercury + 1 * Velocidade ) % 360; //58.6
	rotY_venus = (rotY_venus + 1 * Velocidade) % 360; //243
	rotY = (rotY + 1 * Velocidade) % 360; //0.99
	rotY_marte = (rotY_marte + 1 * Velocidade) % 360; //1.03
	rotY_jupiter = (rotY_jupiter + 1 * Velocidade) % 360; //0.41
	rotY_saturno = (rotY_saturno + 1 * Velocidade) % 360; //0.45
	rotY_urano = (rotY_urano + 1 * Velocidade) % 360; //0.72
	rotY_netuno = (rotY_netuno + 1 * Velocidade/6) % 360; //0.67
	

	//mercury
	angulo_mecury += VelocidadeTranslacao / 4,77;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

	//venus
	angulo_venus += VelocidadeTranslacao / 12,27;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

	//Terra
	angulo += VelocidadeTranslacao/20;
	if (angulo > 360.0)
	angulo = angulo - 360.0;

	//marte
	angulo_marte += VelocidadeTranslacao / 37.59;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

	//jupiter
	angulo_jupiter += VelocidadeTranslacao / 237.15;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

	//saturno
	angulo_saturno += VelocidadeTranslacao / 589.15;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

	//urano
	angulo_urano += VelocidadeTranslacao / 1680.16;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

	//netuno
	angulo_netuno += VelocidadeTranslacao / 3295.78;
	if (angulo_mecury > 360)
		angulo_mecury = angulo_mecury - 360;

    glutPostRedisplay();
    glutTimerFunc(10,Timer, 1);
}	

/* Cria e configura a Luz para a cena */
void Iluminar()
{ 
  glLightfv(GL_LIGHT0, GL_AMBIENT, ambiente);
  glLightfv(GL_LIGHT0, GL_DIFFUSE, difusa);
  glLightfv(GL_LIGHT0, GL_POSITION, posicao);
  glLightfv(GL_LIGHT0, GL_SPECULAR, especular);
  glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodelo_ambiente);
  glEnable(GL_LIGHTING);
  glEnable(GL_LIGHT0);
  glEnable(GL_COLOR_MATERIAL);
}

//Lendo arquivo para aplicar texturas.
void carregaTextura(GLuint tex_id, std::string filePath)
{
	unsigned char* imagem;
	int largura, altura, canais;

	stbi_set_flip_vertically_on_load(true);
	imagem = stbi_load(filePath.c_str(), &largura, &altura, &canais, 4);
	if (imagem)
	{
		glBindTexture(GL_TEXTURE_2D, tex_id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, largura, altura, 0, GL_RGBA, GL_UNSIGNED_BYTE, imagem);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		stbi_image_free(imagem);
	}
	else {
		std::cout << "ERRO! Nao foi possivel carregar a imagem " << filePath.c_str() << std::endl;
	}

}


void Inicializar(void)
{
 glClearColor(0.0, 0.0, 0.0, 1.0);
 glEnable(GL_DEPTH_TEST);
 glShadeModel(GL_SMOOTH);
 Iluminar();
 gluPerspective(60.0, Aspecto, 1.0, 30.0);
 glEnable(GL_TEXTURE_2D);
 glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
 gluLookAt(0.0, 0.0, posicao_espectador_z, 0.0, 0.0, 0.0, 0.0, 1, 0.0);
 
 glGenTextures(1, &logoUERN);
 glGenTextures(1, &espaco);
 glGenTextures(1, &sol);
 glGenTextures(1, &mercury);
 glGenTextures(1, &venus);
 glGenTextures(1, &terra);
 glGenTextures(1, &marte);
 glGenTextures(1, &jupiter);
 glGenTextures(1, &saturno);
 glGenTextures(1, &urano);
 glGenTextures(1, &netuno);

 carregaTextura(logoUERN, "imagens/UERN.png");
 carregaTextura(espaco, "imagens/espaco2.png");
 carregaTextura(sol, "imagens/sol.png");
 carregaTextura(mercury, "imagens/mercury.png");
 carregaTextura(venus, "imagens/venus.png");
 carregaTextura(terra, "imagens/terra.png");
 carregaTextura(marte, "imagens/marte.png");
 carregaTextura(jupiter, "imagens/jupiter.png");
 carregaTextura(saturno, "imagens/saturno.png");
 carregaTextura(urano, "imagens/urano.png");
 carregaTextura(netuno, "imagens/netuno.png");
 
 // Posicionando o observador virtual
 Angulo=60;
 rotX = 20;
 rotY = 0;
 obsZ = 1; 
}

void Espaco(void)
{
	glPushMatrix();
	glColor3f(1.0, 1.0, 1.0);
	glBegin(GL_QUADS);
	glTexCoord2f(0.0f, 0.0f); glVertex2f(-posicao_espaco_x, -posicao_espaco_y);
	glTexCoord2f(0.0f, 1.0f); glVertex2f(-posicao_espaco_x, posicao_espaco_x);
	glTexCoord2f(1.0f, 1.0f); glVertex2f(posicao_espaco_x, posicao_espaco_x);
	glTexCoord2f(1.0f, 0.0f); glVertex2f(posicao_espaco_x, -posicao_espaco_x);
	glEnd();
	glPopMatrix();
}

//Criando esfera personalizada (gluSphere)

void Esfera(float raio,int longitude,int latitude)
{
 GLUquadricObj* q = gluNewQuadric();
 gluQuadricDrawStyle(q, GLU_FILL);
 gluQuadricNormals(q, GLU_SMOOTH);
 gluQuadricTexture(q, GL_TRUE);
 gluSphere(q, raio, longitude, latitude);
 gluDeleteQuadric(q);
}

//////////////////////////////////// Desenha um texto na janela GLUT  ////////////////////////////////////
void DesenhaTexto18(char* string, float posicao_texto_x, float posicao_texto_y)
{
	glPushMatrix();
	// Posição no universo onde o texto será colocado          
	glRasterPos2f(posicao_texto_x, posicao_texto_y);
	// Exibe caracter a caracter
	while (*string)
		glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, *string++); //Texto tamanho 18
	glPopMatrix();
}

void DesenhaTexto12(char* string, float posicao_texto_x, float posicao_texto_y)
{
	glPushMatrix();
	// Posição no universo onde o texto será colocado          
	glRasterPos2f(posicao_texto_x, posicao_texto_y);
	// Exibe caracter a caracter
	while (*string)
		glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12, *string++); //Texto tamanho 12
	glPopMatrix();
}
//////////////////////////////////// Desenha um texto na janela GLUT  ////////////////////////////////////

void LogoUERN(void)
{
	glPushMatrix();
	 glColor3f(1.0, 1.0, 1.0);
	 glBegin(GL_QUADS);
	  glTexCoord2f(0.0f, 0.0f); glVertex2f(-1.5, -1.5);
	  glTexCoord2f(0.0f, 1.0f); glVertex2f(-1.5, 1.5);
	  glTexCoord2f(1.0f, 1.0f); glVertex2f(1.5, 1.5);
	  glTexCoord2f(1.0f, 0.0f); glVertex2f(1.5, -1.5);
	 glEnd();
	glPopMatrix();
}


void TelaInicial() {
	
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glBindTexture(GL_TEXTURE_2D, logoUERN);
	 LogoUERN();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();


	glPushMatrix();
	 char texto[14] = "Sistema Solar";
	 char Computacao_grafica [19] = "Computacao Grafica";
	
	 glColor3f(0.0, 0.0, 1.0);
	 DesenhaTexto18(Computacao_grafica,posicao_texto_x-1.5, posicao_texto_y+3.5);

	 glColor3f(1.0, 0.2, 0.0);
	 DesenhaTexto12(texto, posicao_texto_x - 0.7, posicao_texto_y + 3.0);
	glPopMatrix();
	

	glutSwapBuffers();
}


void verSistemaSolar() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();
	
	glPushMatrix();
	//////////////////////////////////////////////////////////   Sistema solar   //////////////////////////////////////////////////////////
	/////////////////////////////   sol   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	  glRotatef((GLfloat)rotY_sol, 1.0, 0.0, 0.0);
	  glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	  //Define a refletância do material
	  glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	  //Define a concentração do brilho
	  glMateriali(GL_FRONT, GL_SHININESS, 20);
	  //Habilita a textura e cria a esfera
	  glBindTexture(GL_TEXTURE_2D, sol);
	  Esfera(raio_sol, 50, 50);
	  glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   mercury   /////////////////////////////
	glPushMatrix();
	 glTranslatef((0.058 + raio_sol) * cos(angulo_mecury) + (0.058 + raio_sol) * sin(angulo_mecury), 0.0, -(0.058 + raio_sol) * sin(angulo_mecury) + (0.058 + raio_sol) * cos(angulo_mecury));
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_mercury, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, mercury);
	   Esfera(raio_mercury, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   venus   /////////////////////////////
	glPushMatrix();
	 glTranslatef((0.0108 + raio_sol) * cos(angulo_venus) + (0.0108 + raio_sol) * sin(angulo_venus), 0.0, -(0.0108 + raio_sol) * sin(angulo_venus) + (0.0108 + raio_sol) * cos(angulo_venus));
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_venus, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, venus);
	   Esfera(raio_venus, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   terra   /////////////////////////////
	//Armazena a situação atual da pilha de matrizes 
	glPushMatrix();
	 glTranslatef((0.0150 + raio_sol) * cos(angulo) + (0.0150 + raio_sol) * sin(angulo), 0.0, -(0.0150 + raio_sol) * sin(angulo) + (0.0150 + raio_sol) * cos(angulo));
	 glPushMatrix();
	 //glTranslatef(1.5 * cos(angulo) + 1.5 * sin(angulo), 0.0, -1.5 * sin(angulo) + 1.5 * cos(angulo));
	 glPushMatrix();
	 glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, terra);
	   Esfera(raio_terra, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   marte   /////////////////////////////
	glPushMatrix();
	 glTranslatef((0.0230 + raio_sol) * cos(angulo_marte) + (0.0230 + raio_sol) * sin(angulo_marte), 0.0, -(0.0230 + raio_sol) * sin(angulo_marte) + (0.0230 + raio_sol) * cos(angulo_marte));
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_marte, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, marte);
	   Esfera(raio_marte, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   jupiter   /////////////////////////////
	glPushMatrix();
	 glTranslatef((0.78 + raio_sol) * cos(angulo_jupiter) + (0.78 + raio_sol) * sin(angulo_jupiter), 0.0, -(0.78 + raio_sol) * sin(angulo_jupiter) + (0.78 + raio_sol) * cos(angulo_jupiter));
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_jupiter, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, jupiter);
	   Esfera(raio_jupiter, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   saturno   /////////////////////////////
	glPushMatrix();
	 glTranslatef((1.43 + raio_sol) * cos(angulo_saturno) + (1.43 + raio_sol) * sin(angulo_saturno), 0.0, -(1.43 + raio_sol) * sin(angulo_saturno) + (1.43 + raio_sol) * cos(angulo_saturno));
	 glPushMatrix();
	 glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_saturno, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, saturno);
	   Esfera(raio_saturno, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   urano   /////////////////////////////
	glPushMatrix();
	 glTranslatef((2.87 + raio_sol) * cos(angulo_urano) + (2.87 + raio_sol) * sin(angulo_urano), 0.0, -(2.87 + raio_sol) * sin(angulo_urano) + (2.87 + raio_sol) * cos(angulo_urano));
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_urano, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, urano);
	   Esfera(raio_urano, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	/////////////////////////////   netuno   /////////////////////////////
	glPushMatrix();
	 glTranslatef((4.5 + raio_sol) * cos(angulo_netuno) + (4.5 + raio_sol) * sin(angulo_netuno), 0.0, -(4.5 + raio_sol) * sin(angulo_netuno) + (4.5 + raio_sol) * cos(angulo_netuno));
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_netuno, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, netuno);
	   Esfera(raio_netuno, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();
	//////////////////////////////////////////////////////////   Sistema solar   //////////////////////////////////////////////////////////
	glPopMatrix();
	
	glutSwapBuffers();
}

void verMercury() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9
	
	/////////////////////////////   Mercurio  /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0,0.0,0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_mercury, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, mercury);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();
	
	glutSwapBuffers();
}

void verVenus() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9
	
	/////////////////////////////   Venus   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0,0.0,0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_venus, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, venus);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();
	
	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();
}

void verTerra() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	/////////////////////////////   Terra   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, terra);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();
	
	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();

}

void verMarte() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	/////////////////////////////   Marte   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_marte, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, marte);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();
}

void verJupiter() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	/////////////////////////////   Jupiter   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_jupiter, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, jupiter);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();
}

void verSaturno() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	/////////////////////////////   Saturno   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_saturno, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, saturno);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();
}

void verUrano() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	/////////////////////////////   Urano   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_urano, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, urano);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();
}

void verNetuno() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9

	/////////////////////////////   Netuno   /////////////////////////////
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glPushMatrix();
	  glRotatef(9, 0.0, 0.0, 1.0);
	  glPushMatrix();
	   glRotatef((GLfloat)rotY_netuno, 1.0, 0.0, 0.0);
	   glRotatef((GLfloat)1.0, 0.0, obsZ, 0.0);
	   //Define a refletância do material
	   glMaterialfv(GL_FRONT, GL_SPECULAR, semespecular);
	   //Define a concentração do brilho
	   glMateriali(GL_FRONT, GL_SHININESS, 20);
	   //Habilita a textura e cria a esfera 
	   glBindTexture(GL_TEXTURE_2D, netuno);
	   Esfera(2.5, 50, 50);
	   glBindTexture(GL_TEXTURE_2D, 0);
	  glPopMatrix();
	 glPopMatrix();
	glPopMatrix();

	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();
}

void Creditos() {
	
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0, 1.0, 1.0);
	//Armazena o estado anterior para rotação da posição da luz 
	
	
	glPushMatrix();
	 glRotated((GLdouble)posicaoluz, 1.0, 0.0, 0.0);
	 glLightfv(GL_LIGHT0, GL_POSITION, posicao);
	glPopMatrix(); // Posição da Luz 9
 	
	//Espaço
	glPushMatrix();
	 glTranslatef(0.0, 0.0, -9.0);
	 glBindTexture(GL_TEXTURE_2D, espaco);
	 Espaco();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	//textos
	glPushMatrix();
	 char texto[14] = "Sistema Solar";
	 char Computacao_grafica[19] = "Computacao Grafica";
	 char lima_junio[23] = "Professor: Lima Junior";
	 char alunos[31] = "Joao Lucas & Antonio Frabricio";

	 glColor3f(0.0, 0.0, 1.0);
	 DesenhaTexto18(Computacao_grafica, posicao_texto_x - 1.5, posicao_texto_y + 3.5);

	 glColor3f(1.0, 1.0, 1.0);
	 DesenhaTexto12(lima_junio, posicao_texto_x - 1.0, posicao_texto_y + 3.0);
	 glPopMatrix();

	 glColor3f(1.0, 1.0, 1.0);
	 DesenhaTexto12(alunos, posicao_texto_x - 1.6, posicao_texto_y + 2.5);
	 glPopMatrix();

	//Logo UERN
	glPushMatrix();
	 glTranslatef(0.0, 0.0, 0.0);
	 glBindTexture(GL_TEXTURE_2D, logoUERN);
	 LogoUERN();
	 glBindTexture(GL_TEXTURE_2D, 0);
	glPopMatrix();

	glutSwapBuffers();

}

void Desenhar(void)
{
	
	if (escolhaVisualizacao == 0) {
		TelaInicial();
		posicao_espectador_z = 10;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0, Aspecto, 1.0, 30.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		gluLookAt(0.0, 0.0, posicao_espectador_z, 0.0, 0.0, 0.0, 0.0, 1, 0.0);
	}
	else if (escolhaVisualizacao == 1)
		verSistemaSolar();
	else if (escolhaVisualizacao == 2)
		verMercury();
	else if (escolhaVisualizacao == 3)
		verVenus();
	else if (escolhaVisualizacao == 4)
		verTerra();
	else if (escolhaVisualizacao == 5)
		verMarte();
	else if (escolhaVisualizacao == 6)
		verJupiter();
	else if (escolhaVisualizacao == 7)
		verSaturno();
	else if (escolhaVisualizacao == 8)
		verUrano();
	else if (escolhaVisualizacao == 9)
		verNetuno();
	else if (escolhaVisualizacao == 10) {
		Creditos();
		posicao_espectador_z = 10;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0, Aspecto, 1.0, 30.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		gluLookAt(0.0, 0.0, posicao_espectador_z, 0.0, 0.0, 0.0, 0.0, 1, 0.0);
	}
}

void UsarTeclado(unsigned char key, int x, int y)
{
	switch (key) {
	case 'd':
	case 'D':
  	  glDisable(GL_TEXTURE_2D);
      glutPostRedisplay();
	break;
	case 'A':
	case 'a':
      glEnable(GL_TEXTURE_2D);
      glutPostRedisplay();
	break;
    case 'P':
	case 'p':
	  Velocidade = 0;
	  VelocidadeTranslacao = 0.0;
      glutPostRedisplay();
	break;
    case 'C':
    case 'c':
	  Velocidade = 2;
	  VelocidadeTranslacao = 0.1;
      glutPostRedisplay();
    break;
	case VK_ESCAPE:
		exit(0);
	break;
}
}

void TecladoEspecial(int key, int x, int y) {
	
	switch (key) {
	case GLUT_KEY_UP:
		if (posicao_espectador_z > 3.5 and escolhaVisualizacao != 0 and escolhaVisualizacao != 10) {
			posicao_espectador_z = posicao_espectador_z - 0.5;
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(60.0, Aspecto, 1.0, 30.0);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			gluLookAt(0.0, 0.0, posicao_espectador_z, 0.0, 0.0, 0.0, 0.0, 1, 0.0);
		}
		break;
	case GLUT_KEY_DOWN:
		if (posicao_espectador_z < 18.0 and escolhaVisualizacao  != 0 and escolhaVisualizacao != 10) {
			posicao_espectador_z = posicao_espectador_z + 0.5;
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(60.0, Aspecto, 1.0, 30.0);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			gluLookAt(0.0, 0.0, posicao_espectador_z, 0.0, 0.0, 0.0, 0.0, 1, 0.0);
		}
		
		break;
	
	}
	glutPostRedisplay();
}

void verPlanetas(int op)
{
	switch (op) {
	case 0:
		escolhaVisualizacao = 0;
		break;
	case 1:
		escolhaVisualizacao = 1;
		Velocidade = 2;
		VelocidadeTranslacao = 0.1;
		break;
	case 2:
		escolhaVisualizacao = 2;
		Velocidade = 1;
		break;
	case 3:
		escolhaVisualizacao = 3;
		Velocidade = 1;
		break;
	case 4:
		escolhaVisualizacao = 4;
		Velocidade = 1;
		break;
	case 5:
		escolhaVisualizacao = 5;
		Velocidade = 1;
		break;
	case 6:
		escolhaVisualizacao = 6;
		Velocidade = 1;
		break;
	case 7:
		escolhaVisualizacao = 7;
		Velocidade = 1;
		break;
	case 8:
		escolhaVisualizacao = 8;
		Velocidade = 1;
		break;
	case 9:
		escolhaVisualizacao = 9;
		Velocidade = 1;
		break;
		
		
		
	
	}
	glutPostRedisplay();

}

void MenuControle(int op)
{
	switch (op) {
	
		Velocidade = 0;
		VelocidadeTranslacao = 0.0;
		break;
	case 1:
		if (ativa_animacao == 1) {

			glutChangeToMenuEntry(1, "Ativar Animacao", 1);
			Velocidade = 0;
			VelocidadeTranslacao = 0.0;
			ativa_animacao = 0;
		}
		else if (ativa_animacao == 0) {
			glutChangeToMenuEntry(1, "Parar Animacao", 1);
			if (escolhaVisualizacao == 1)
				Velocidade = 2;
			else
				Velocidade = 1;
			
			VelocidadeTranslacao = 0.1;
			ativa_animacao = 1;
		}
		
		break;
	case 2:
		VelocidadeTranslacao += 1.0;
		break;
	case 3:
		
		if (janela_ativa == 0) {
			
			glutChangeToMenuEntry(3, "Creditos", 3);
			escolhaVisualizacao = 0;
			janela_ativa = 1;
		}
		else if (janela_ativa == 1) {
			glutChangeToMenuEntry(3, "Tela Inicial", 3);
			escolhaVisualizacao = 10;
			janela_ativa = 0;
		}

		break;
	}

	glutPostRedisplay();
}

void MenuPrincipal(int op)
{
	// Menu principal vazio, só pra gerenciar os submenus
}

void GerenciaMouse(int button, int state, int x, int y)
{

	if (button == GLUT_RIGHT_BUTTON)
		if (state == GLUT_DOWN)
			CriaMenu();

	glutPostRedisplay();
}

void CriaMenu()
{
	int menu, submenu1, submenu2;
	//0-Tela Inicial, 1-Sistema Solar, 2-Mercurio, 3-Venus, 4-Terra, 5-Marte,  6-Jupiter, 7-Saturno, 8-Urano , 9-Netuno, 10-Creditos
	submenu1 = glutCreateMenu(verPlanetas);
	glutAddMenuEntry("Sistema Solar", 1);
	glutAddMenuEntry("Mercurio", 2);
	glutAddMenuEntry("Venus", 3);
	glutAddMenuEntry("Terra", 4);
	glutAddMenuEntry("Marte", 5);
	glutAddMenuEntry("Jupiter", 6);
	glutAddMenuEntry("Saturno", 7);
	glutAddMenuEntry("Urano", 8);
	glutAddMenuEntry("Netuno", 9);

	submenu2 = glutCreateMenu(MenuControle);
	glutAddMenuEntry("Parar Animacao", 1);
	glutAddMenuEntry("Aumentar velocidade de translacao", 2);
	glutAddMenuEntry("Creditos", 3);
	
	menu = glutCreateMenu(MenuPrincipal);
	glutAddSubMenu("Escolher Planetas e sistema solar", submenu1);
	glutAddSubMenu("Controles", submenu2);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
}


int main()
{
  glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB);
  glutInitWindowSize (800, 600);
  glutInitWindowPosition (100, 100);
  glutCreateWindow ("Sistema solar");
  Inicializar();
  glutDisplayFunc(Desenhar);
  glutReshapeFunc(AjustarJanela);
  glutKeyboardFunc(UsarTeclado);
  glutSpecialFunc(TecladoEspecial);
  glutMouseFunc(GerenciaMouse);
  glutTimerFunc(10,Timer, 1);
  glutMainLoop();
  glDisable(GL_TEXTURE_2D);
  glDeleteTextures(1, &terra);
  return 0;
}


