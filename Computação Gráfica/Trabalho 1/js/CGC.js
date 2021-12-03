function enter(c, dx, dy, sx, sy)
{
    c.save();
    c.translate(dx, dy);
    c.scale(sx, sy);
}

function leave(c, fs, ss)
{
    c.restore();
    c.lineWidth = 1.5;
    c.fillStyle = fs;
    c.strokeStyle = ss;
    c.fill();
    c.stroke();
}

function pescoço(c)
{
	c.beginPath();
		c.moveTo(180, 235);
		c.lineTo(157, 235);
		c.bezierCurveTo(100, 350, 40, 520, 250, 440);
		c.bezierCurveTo(420, 220, 430, 280, 432, 315);
		c.lineTo(460, 315);
		c.bezierCurveTo(450, 260, 430, 190, 295, 335);
		c.bezierCurveTo(230, 420, 120, 420, 218, 340);
		c.lineTo(193, 250);
		c.lineTo(180, 235);
	c.closePath();
}

function cara(c)
{
	c.beginPath();
        c.ellipse(170, 190, 45, 50, 0, 0, 2 * Math.PI);
    c.closePath();
}

function olho_esquerdo(c)
{
	c.beginPath();
		c.ellipse(151, 192, 5, 5, 0, 0, 2 * Math.PI);
	c.closePath();	
}

function olho_direito(c)
{
	c.beginPath();
		c.ellipse(188, 192, 5, 5, 0, 0, 2 * Math.PI);
	c.closePath();	
}

function boca(c)
{
	c.beginPath();
		c.moveTo(157, 218);
		c.quadraticCurveTo(170, 229, 183, 218);
		c.quadraticCurveTo(188, 210, 181, 214);
		c.quadraticCurveTo(170, 221, 159, 214);
		c.quadraticCurveTo(153, 213, 157, 218);
	c.closePath();
}

function orelha_esquerda(c)
{
	c.beginPath();
		c.lineJoin = "round";
		c.moveTo(125, 182);
		c.lineTo(117, 176);
		c.lineTo(119, 186);
		c.lineTo(120, 188);
		c.lineTo(121, 191);
		c.lineTo(124, 194);
		c.lineTo(125, 195);
	c.closePath();
}

function brinco_esquerdo(c)
{
	c.beginPath();
		c.moveTo(122, 185);
		c.bezierCurveTo(130, 200, 110, 200, 119, 186);
		c.lineWidth = 2;
		c.strokeStyle = 'gold';
		c.stroke();
	c.closePath();
}

function cabelo(c)
{
	c.beginPath();
		c.moveTo(140, 153);
		c.bezierCurveTo(175, 135, 185, 200, 240, 130);
		c.bezierCurveTo(210, 145, 185, 145, 218, 130);
		c.bezierCurveTo(200, 140, 150, 100, 140, 153);
	c.closePath();
}

function nariz(c)
{
	c.beginPath();
		c.moveTo(165, 205);
		c.lineTo(165, 210);
		c.lineTo(175, 210);
		c.lineTo(175, 205);
		c.lineTo(165, 205);
	c.closePath();
}

function colete_esquerdo(c)
{
	c.beginPath();
		c.arc(93, 290, 65, 1.77 * Math.PI, 2.48 * Math.PI, false);
		c.fillStyle = "darkorange";
		c.fill();
		c.lineWidth = 8;
		c.strokeStyle = "gold";
		c.stroke();
	c.closePath();
}

function curva_escura_corpo(c)
{
	c.beginPath();
		c.moveTo(180, 340);
		c.bezierCurveTo(90, 420, 150, 460, 165, 460);
		c.lineTo(180, 459);
		c.bezierCurveTo(135, 447, 155, 410, 195, 360);
		c.lineTo(180, 340);
	c.closePath();
}

function colete_direito(c)
{
	c.beginPath();
		c.moveTo(197, 243);
		c.lineTo(233, 300);
		c.lineTo(206, 370);
		c.bezierCurveTo(170, 340, 155, 300, 197, 243);
	c.closePath();
}

function colete_contorno(c)
{
	c.beginPath();
		c.moveTo(206, 370);
		c.bezierCurveTo(170, 340, 155, 300, 197, 243);
		c.lineWidth = 8;
		c.strokeStyle = "gold";
		c.stroke();
	c.closePath();
}

function sobrancelha_esquerda(c)
{
	c.beginPath();
		c.moveTo(140, 178);
		c.bezierCurveTo(150, 167, 176, 185, 152, 179);
		c.bezierCurveTo(150, 177, 140, 176, 140, 178);
	c.closePath();
}

function S_cauda(c)
{
	c.beginPath();
			c.moveTo(250, 440);
			c.bezierCurveTo(390, 330, 300, 350, 400, 252);
			c.bezierCurveTo(290, 300, 285, 340, 250, 440);

			c.strokeStyle = "black";
			c.stroke();
	c.closePath();
}

function braco_baixo(c)
{
	c.beginPath();
		c.moveTo(110, 280);
		c.bezierCurveTo(60, 310, 140, 315, 190, 280);
		c.lineTo(196, 280);
		c.bezierCurveTo(210, 289, 270, 300, 206, 265);
		c.bezierCurveTo(160, 260, 150, 280, 130, 275);
		c.lineTo(110, 280);
	c.closePath();
}

function braco_cima(c)
{
	c.beginPath();
		c.moveTo(240, 285);
		c.bezierCurveTo(270, 320, 165, 305, 140, 288);
		c.quadraticCurveTo(125, 288, 119, 284);
		c.bezierCurveTo(89, 291, 90, 284, 127, 268);
		c.lineTo(200, 280);
		c.quadraticCurveTo(234, 295, 240, 285);

		c.strokeStyle = "black";
		c.stroke();
	c.closePath();
}

function comprimento_lamparina(c)
{
	c.beginPath();
		c.moveTo(450, 275);
		c.bezierCurveTo(457, 340, 350, 315, 370, 400);
		c.bezierCurveTo(380, 425, 460, 480, 485, 435);
		c.bezierCurveTo(420, 435, 410, 420, 385, 410);
		c.bezierCurveTo(350, 300, 497, 380, 450, 275);
	c.closePath();
}

function base_lamparina(c)
{
	c.beginPath();
		c.moveTo(455, 463);
		c.quadraticCurveTo(454, 456, 445, 452);
		c.quadraticCurveTo(440, 452, 435, 450);
		c.quadraticCurveTo(422, 449, 421, 454);
		c.lineTo(455, 463);
	c.closePath();
}

function pega_lamparina_1(c)
{
	c.beginPath();
		c.moveTo(460, 452);
		c.quadraticCurveTo(500, 452, 505, 432);
		c.lineTo(499, 435);
		c.quadraticCurveTo(495, 442, 475, 446);
		c.quadraticCurveTo(472, 450, 460, 452);
	c.closePath();
}

function pega_lamparina_2(c)
{
	c.beginPath();
		c.moveTo(499, 435);
		c.quadraticCurveTo(450, 440, 410, 423);
		c.quadraticCurveTo(435, 430, 440, 415);
		c.lineTo(456, 419);
		c.quadraticCurveTo(459, 433, 505, 432);
		c.lineTo(499, 435);
	c.closePath();
}

function meia_tampa(c)
{
	c.beginPath();
		c.moveTo(440, 415);
		c.lineTo(448, 406);
		c.lineTo(450, 403);
		c.lineTo(454, 404);
		c.lineTo(454, 406);
		c.lineTo(456, 419);
		c.lineTo(440, 415);
	c.closePath();
}

function topo_tampa(c)
{
	c.beginPath();
		c.moveTo(450, 403);
		c.quadraticCurveTo(448, 400, 453, 397);
		c.quadraticCurveTo(457, 401, 454, 404);
		c.lineTo(450, 403);
	c.closePath();
}

function arco_dourado(c)
{
	c.beginPath();
		c.moveTo(400, 334);
		c.bezierCurveTo(400, 330, 452, 321, 450, 270);
		c.bezierCurveTo(450, 260, 425, 208, 375, 245);
		c.bezierCurveTo(300, 330, 300, 410, 250, 440);
		c.bezierCurveTo(310, 400, 330, 305, 350, 288);
		c.bezierCurveTo(420, 180, 485, 285, 400, 334);
	c.closePath();
}

//------------------------

function sol(c)
{
	c.beginPath();
		c.ellipse(100, 100, 50, 50, 0, 0, 2 * Math.PI);
	c.closePath();
}

function céu(c)
{
	c.beginPath();
		c.moveTo(0, 0);
		c.lineTo(600, 0);
		c.lineTo(600, 600);
		c.lineTo(0, 600);
		c.lineTo(0, 0);
	c.closePath();
}

function topo_deserto(c)
{
	c.beginPath();
		c.moveTo(0, 320);
		c.lineTo(300, 250);
		c.lineTo(400, 300);
		c.lineTo(540, 220);
		c.lineTo(600, 250);
		c.lineTo(600, 600);
		c.lineTo(0, 600);
		c.lineTo(0, 320);
	c.closePath();
}

function parte_deserto1(c)
{
	c.beginPath();
		c.moveTo(450, 600);
		c.quadraticCurveTo(500, 500, 600, 500);
		c.lineTo(600, 450);
		c.bezierCurveTo(570, 430, 350, 600, 130, 520);
		c.quadraticCurveTo(70, 500, 0, 510);
		c.bezierCurveTo(100, 490, 100, 600, 350, 600);
		c.lineTo(450, 600);
	c.closePath();
}

function parte_deserto2(c)
{
	c.beginPath();
		c.moveTo(400, 300);
		c.bezierCurveTo(460, 320, 570, 320, 545, 305);
		c.bezierCurveTo(470, 270, 560, 260, 540, 220);
	c.closePath();
}

function parte_deserto3(c)
{
	c.beginPath();
		c.moveTo(0, 400);
		c.lineTo(350, 360);
		c.quadraticCurveTo(200, 450, 85, 470);
		c.lineTo(60, 507);
		c.quadraticCurveTo(25, 505, 0, 510);
		c.lineTo(0, 400);
	c.closePath();
}

function parte_deserto4(c)
{
	c.beginPath();
		c.moveTo(60, 507);
		c.quadraticCurveTo(550, 340, 600, 350);
		c.bezierCurveTo(550, 355, 400, 410, 500, 415);
		c.bezierCurveTo(490, 430, 150, 480, 350, 530);
		c.bezierCurveTo(200, 570, 80, 497, 60, 507);
	c.closePath();
}

function parte_deserto5(c)
{
	c.beginPath();
		c.moveTo(353, 360);
		c.lineTo(410, 392);
		c.lineTo(60, 507);
		c.lineTo(85, 470);
		c.quadraticCurveTo(200, 450, 350, 360);
	c.closePath();
}

function parte_deserto6(c)
{
	c.beginPath();
		c.moveTo(600, 350);
		c.bezierCurveTo(550, 355, 400, 410, 500, 415);
		c.bezierCurveTo(490, 430, 150, 480, 350, 530);
		c.bezierCurveTo(450, 520, 560, 440, 600, 450);
		c.lineTo(600, 350);
	c.closePath();
}


//------------------------

function formato_camelo(c)
{
	c.beginPath();
		c.moveTo(200, 350);
		c.quadraticCurveTo(270, 430, 350, 370);
		c.lineTo(335, 455);
		c.quadraticCurveTo(322, 465, 330, 480);
		c.quadraticCurveTo(310, 550, 325, 545);
		c.lineTo(355, 545);
		c.bezierCurveTo(355, 515, 330, 545, 344, 485);
		c.quadraticCurveTo(353, 478, 352, 458);
		c.lineTo(380, 375);
		c.lineTo(365, 350);
		c.lineTo(380, 375);
		c.quadraticCurveTo(380, 400, 400, 445);
		c.quadraticCurveTo(390, 465, 415, 480);
		c.lineTo(440, 545);
		c.lineTo(470, 545);
		c.bezierCurveTo(470, 515, 460, 545, 430, 480);
		c.quadraticCurveTo(435, 460, 420, 440);
		c.quadraticCurveTo(400, 350, 425, 350);
		c.quadraticCurveTo(500, 350, 500, 225);
		c.quadraticCurveTo(505, 220, 508, 210);
		c.quadraticCurveTo(525, 215, 545, 200);
		c.bezierCurveTo(590, 210, 570, 170, 550, 175);
		c.bezierCurveTo(520, 175, 520, 150, 490, 165);
		c.quadraticCurveTo(480, 170, 475, 180);
		c.bezierCurveTo(440, 200, 480, 370, 390, 270);
		c.bezierCurveTo(360, 210, 350, 270, 320, 200);
		c.quadraticCurveTo(260, 120, 200, 200);
		c.bezierCurveTo(180, 240, 140, 240, 130, 280);
		c.lineTo(127, 283);
		c.quadraticCurveTo(110, 290, 100, 335);
		c.bezierCurveTo(100, 360, 80, 350, 80, 380);
		c.bezierCurveTo(110, 380, 110, 280, 127, 300);
		c.lineTo(130, 303);
		c.quadraticCurveTo(130, 380, 70, 410);
		c.quadraticCurveTo(40, 545, 65, 545);
		c.lineTo(90, 545);
		c.bezierCurveTo(90, 520, 70, 530, 75, 500);
		c.lineTo(85, 455);
		c.quadraticCurveTo(105, 450, 110, 420);
		c.lineTo(150, 370);
		c.lineTo(160, 420);
		c.quadraticCurveTo(150, 435, 172, 455);
		c.lineTo(210, 545);
		c.lineTo(240, 545);
		c.bezierCurveTo(240, 520, 220, 530, 210, 500);
		c.quadraticCurveTo(180, 420, 190, 390);
		c.quadraticCurveTo(205, 360, 200, 350);
	c.closePath();
}

function olho_camelo(c)
{
	c.beginPath();
		c.ellipse(512, 173, 5, 3, 0, 0, 2 * Math.PI);
	c.closePath();
}

function olho_camelo_dentro(c)
{
	c.beginPath();
		c.ellipse(514, 173, 0.5, 0.5, 0, 0, 2 * Math.PI);
	c.closePath();
}

function boca_camelo(c)
{
	c.beginPath();
		c.moveTo(572, 192);
		c.quadraticCurveTo(560, 195, 545, 190);

		c.strokeStyle = "black";
		c.stroke();
	c.closePath();
}

function nariz_camelo(c)
{
	c.beginPath();
		c.moveTo(567, 180);
		c.lineTo(560, 180);
		c.lineTo(560, 178);
		c.lineTo(560, 182);

		c.strokeStyle = "black";
		c.stroke();
	c.closePath();
}


//------------------------
function mestre(c)
{
	//desenha corpo
    enter(c, 0, 0, 1, 1);
    pescoço(c);
    let a = c.createLinearGradient(0, 0, 300, 400);
    	a.addColorStop(0.2, "deepskyblue");
    	a.addColorStop(1.0, "dodgerblue");
    leave(c, a, "rgb(0, 100, 150)");

	//desenha o formato da cara
	enter(c, 0, 0, 1, 1);
    cara(c);
    let b = c.createLinearGradient(175, 185, 175, 220);
    	b.addColorStop(0.1, "deepskyblue");
    	b.addColorStop(1.0, "dodgerblue");
    leave(c, b, "rgb(0, 100, 150)");
    
    //desenha olho esquerdo
    enter(c, 0, 0, 1, 1);
    olho_esquerdo(c);
    leave(c, "black", "black");

    //desenha olho direito
    enter(c, 0, 0, 1, 1);
    olho_direito(c);
    leave(c, "black", "black");

    //desenha a boca
    enter(c, 0, 0, 1, 1);
    boca(c);
    leave(c, "brown", "brown");

    //desenha orelha esquerda
    enter(c, 0, 0, 1, 1);
    orelha_esquerda(c);
    leave(c, "dodgerblue", "rgb(0, 100, 150)");

    //desenha orelha direita
    enter(c, 340, 0, -1, 1);
    orelha_esquerda(c);
    leave(c, "dodgerblue", "rgb(0, 100, 150)");

    //desenha brinco esquerdo
    enter(c, 0, 0, 1, 1);
    brinco_esquerdo(c);
    c.restore();

    //desenha brinco direito
    enter(c, 340, 0, -1, 1);
    brinco_esquerdo(c);
    c.restore();

    //desenha cabelo
    enter(c, 0, 0, 1, 1);
    cabelo(c);
    leave(c, "black", "black");

    //desenha nariz
    enter(c, 0, 0, 1, 1);
    nariz(c);
    leave(c, "rgb(50,60,250)", "royalblue");

    //colete esquerdo completo
    enter(c, 0, 0, 1, 1);
    colete_esquerdo(c);
    c.restore();

    //desenha a curva escura do corpo
    enter(c, 0, 0, 1, 1);
    curva_escura_corpo(c);
    let d = c.createLinearGradient(80, 350, 220, 330);
    	d.addColorStop(0.2, "navy");
    	d.addColorStop(1.0, "deepskyblue");
    leave(c, d, d);

    //desenha colete direito laranja
    enter(c, 0, 0, 1, 1);
    colete_direito(c);
    leave(c, "darkorange", "darkorange");

    //desenha colete direito contorno amarelo
    enter(c, 0, 0, 1, 1);
    colete_contorno(c);
    c.restore();

    //desenha sobrancelha esquerda
    enter(c, 0, 1, 1, 1);
    sobrancelha_esquerda(c);
    leave(c, "rgb(50,60,250)", "royalblue");

    //desenha sobrancelha direita
    enter(c, 339, 1, -1, 1);
    sobrancelha_esquerda(c);
    leave(c, "rgb(50,60,250)", "royalblue");

    //desenha S cauda 1
    enter(c, 0, 0, 1, 1);
    S_cauda(c);
    let e = c.createLinearGradient(250, 400, 350, 400);
    	e.addColorStop(0.2, "darkorange");
    	e.addColorStop(0.9, "orange");
    	e.addColorStop(1.0, "darkorange");
    leave(c, e, "darkorange");

    //desenha braço de baixo
    enter(c, 5, 0, 1, 1);
    braco_baixo(c);
    let f = c.createLinearGradient(80, 400, 200, 400);
    	f.addColorStop(0.1, "deepskyblue");
    	f.addColorStop(0.8, "royalblue");
    	f.addColorStop(1.0, "deepskyblue");
    leave(c, f, "rgb(0, 100, 150)");

    //desenha braço de cima
    enter(c, 0, 0, 1, 1);
    braco_cima(c);
    let g = c.createLinearGradient(100, 400, 280, 400);
    	g.addColorStop(0.1, "deepskyblue");
    	g.addColorStop(0.8, "royalblue");
    	g.addColorStop(1.0, "deepskyblue");
    leave(c, g, "rgb(0, 100, 150)");

    //desenha comprimento lamparina
    enter(c, 0, 0, 1, 1);
    comprimento_lamparina(c);
    let h = c.createLinearGradient(400, 300, 400, 490);
    	h.addColorStop(0.1, "darkorange");
    	h.addColorStop(0.3, "gold");
    	h.addColorStop(0.5, "darkorange");
    	h.addColorStop(0.8, "gold");
    	h.addColorStop(1.0, "darkorange");
    leave(c, h, "darkorange");

    //desenha base lamparina
    enter(c, 0, 0, 1 ,1);
    base_lamparina(c);
    leave(c, "yellow", "darkorange");

    //desenha pega da lamparina 1
    enter(c, 0, 0, 1, 1);
    pega_lamparina_1(c);
    leave(c, "orange", "darkorange");

    //desenha pega da lamparina 2
    enter (c, 0, 0, 1, 1);
    pega_lamparina_2(c);
    leave(c, "rgb(300, 110, 0)", "darkorange");

    //desenha meia tampa da lamparina
    enter(c, 0, 0, 1, 1);
    meia_tampa(c);
    leave(c, "gold", "darkorange");

    //desenha topo tampa da lamparina
    enter(c, 0, 0, 1, 1);
    topo_tampa(c);
    leave(c, "yellow", "darkorange");

    //desenha arco dourado cauda
    enter(c, 0, 0, 1, 1);
    arco_dourado(c);
    leave(c, "khaki", "khaki");
}

function deserto(c)
{
	//desenha céu
	enter(c, 0, 0, 1, 1);
	céu(c);
	let i = c.createLinearGradient(0, 80, 0, 400);
		i.addColorStop(0.4, "#01FDE4");
		i.addColorStop(0.8, "#1C68FF");
	leave(c, i, i);

	//desenha sol
	enter(c, 0, 0, 1, 1);
	sol(c);
	let j = c.createRadialGradient(110, 110,30, 100,100,70);
    	j.addColorStop(0.1, "gold");
    	j.addColorStop(0.4, "yellow");
	leave(c, j, "gold");

	//desenha topo deserto
	enter(c, 0, 0, 1, 1);
	topo_deserto(c);
	leave(c, "#B7882A", "#B7882A");

	//desenha parte deserto
	enter(c, 0, 0, 1, 1);
	parte_deserto1(c);
	leave(c, "#DCAF55", "#DCAF55");

	//desenha parte deserto
	enter(c, 0, 0, 1, 1);
	parte_deserto2(c);
	leave(c, "#DCAF55", "#DCAF55");

	//desenha parte deserto
	enter(c, 0, 0, 1, 1);
	parte_deserto3(c);
	leave(c, "#EEC36E", "#EEC36E");

	//desenha parte deserto
	enter(c, 0, 0, 1, 1);
	parte_deserto4(c);
	leave(c, "#EEC36E", "#EEC36E");

	//desenha parte deserto
	enter(c, 0, 0, 1, 1);
	parte_deserto5(c);
	leave(c, "#946609", "#946609");

	//desenha parte deserto
	enter(c, 0, 0, 1, 1);
	parte_deserto6(c);
	leave(c, "#946609", "#946609");
}

function camelo(c)
{
	enter(c, 570, 230, -0.15, 0.15);
	formato_camelo(c);
	let k = c.createLinearGradient(0, 350, 0, 500);
    	k.addColorStop(0.1, "#957128");
    	k.addColorStop(0.8, "#4D3506");
	leave(c, k, "#614310");

	enter(c, 570, 230, -0.15, 0.15);
	olho_camelo(c);
	leave(c, "white", "#957128");

	enter(c, 570, 230, -0.15, 0.15);
	olho_camelo_dentro(c);
	leave(c, "black", "black");

	enter(c, 570, 230, -0.15, 0.15);
	boca_camelo(c);
	c.restore();

	enter(c, 570, 230, -0.15, 0.15);
	nariz_camelo(c);
	c.restore();
}



function anima_fumo_lamparina(c, parameters)
{
	c.beginPath();
		c.moveTo(380, 410);
		c.quadraticCurveTo(300, 370, parameters.x, parameters.y);
		c.lineTo(390, 405);
		c.lineTo(380, 410);

		c.fillStyle = "rgba(238, 202, 156, 0.6)";
		c.fill();
	c.closePath();
}


function update (c, parameters)
{
	c.clearRect(0, 0, 600, 600);
	deserto(c);
	camelo(c);
	mestre(c);
	anima_fumo_lamparina(c, parameters);
	requestAnimationFrame(function() {update(c, parameters);});
	TWEEN.update();
}


function main()
{
    let parameters = {x: 370, y:380};
    let tween = new TWEEN.Tween(parameters)
    	.to({x: 450, y:400}, 2000)
    	.easing(TWEEN.Easing.Cubic.InOut)
    	.yoyo(true)
    	.repeat(Infinity);
    tween.start();


    let c = document
                .getElementById("acanvas")
                .getContext("2d");

    requestAnimationFrame(function() {update(c, parameters);});
}