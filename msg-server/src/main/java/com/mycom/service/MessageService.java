package com.mycom.service;

import java.util.List;

import com.mycom.model.Message;

public interface MessageService {
	
	/**
	 * Lists numOf amount of messages (newest first) that correspond to language lang.
	 * @param numOf Amount of results to retrieve.
	 * @param lang Code for the language. Possible codes are: 
	 *  all All languages, 
	    aa	Afar
		ab	Abkhazian
		af	Afrikaans
		ak	Akan
		am	Amharic
		ar	Arabic
		as	Assamese
		ay	Aymara
		az	Azerbaijani
		ba	Bashkir
		be	Belarusian
		bg	Bulgarian
		bh	Bihari
		bi	Bislama
		bn	Bengali
		bo	Tibetan
		br	Breton
		bs	Bosnian
		ca	Catalan
		ceb	Cebuano
		chr	Cherokee
		co	Corsican
		crs	Seselwa
		cs	Czech
		cy	Welsh
		da	Danish
		de	German
		dv	Dhivehi
		dz	Dzongkha
		el	Greek
		en	English
		eo	Esperanto
		es	Spanish
		et	Estonian
		eu	Basque
		fa	Persian
		fi	Finnish
		fj	Fijian
		fo	Faroese
		fr	French
		fy	Frisian
		ga	Irish
		gd	Scots Gaelic
		gl	Galician
		gn	Guarani
		gu	Gujarati
		gv	Manx
		ha	Hausa
		haw	Hawaiian
		hi	Hindi
		hmn	Hmong
		hr	Croatian
		ht	Haitian Creole
		hu	Hungarian
		hy	Armenian
		ia	Interlingua
		id	Indonesian
		ie	Interlingue
		ig	Igbo
		ik	Inupiak
		is	Icelandic
		it	Italian
		iu	Inuktitut
		iw	Hebrew
		ja	Japanese
		jw	Javanese
		ka	Georgian
		kha	Khasi
		kk	Kazakh
		kl	Greenlandic
		km	Khmer
		kn	Kannada
		ko	Korean
		ks	Kashmiri
		ku	Kurdish
		ky	Kyrgyz
		la	Latin
		lb	Luxembourgish
		lg	Ganda
		lif	Limbu
		ln	Lingala
		lo	Laothian
		lt	Lithuanian
		lv	Latvian
		mfe	Mauritian Creole
		mg	Malagasy
		mi	Maori
		mk	Macedonian
		ml	Malayalam
		mn	Mongolian
		mr	Marathi
		ms	Malay
		mt	Maltese
		my	Burmese
		na	Nauru
		ne	Nepali
		nl	Dutch
		no	Norwegian
		nr	Ndebele
		nso	Pedi
		ny	Nyanja
		oc	Occitan
		om	Oromo
		or	Oriya
		pa	Punjabi
		pl	Polish
		ps	Pashto
		pt	Portuguese
		qu	Quechua
		rm	Rhaeto Romance
		rn	Rundi
		ro	Romanian
		ru	Russian
		rw	Kinyarwanda
		sa	Sanskrit
		sco	Scots
		sd	Sindhi
		sg	Sango
		si	Sinhalese
		sk	Slovak
		sl	Slovenian
		sm	Samoan
		sn	Shona
		so	Somali
		sq	Albanian
		sr	Serbian
		ss	Siswant
		st	Sesotho
		su	Sundanese
		sv	Swedish
		sw	Swahili
		syr	Syriac
		ta	Tamil
		te	Telugu
		tg	Tajik
		th	Thai
		ti	Tigrinya
		tk	Turkmen
		tl	Tagalog
		tn	Tswana
		to	Tonga
		tr	Turkish
		ts	Tsonga
		tt	Tatar
		ug	Uighur
		uk	Ukrainian
		ur	Urdu
		uz	Uzbek
		ve	Venda
		vi	Vietnamese
		vo	Volapuk
		war	Waray Philippines
		wo	Wolof
		xh	Xhosa
		yi	Yiddish
		yo	Yoruba
		za	Zhuang
		zh	Chinese
		zh-Hant	Chineset
		zu	Zulu
	 * 
	 * @return List of messages that match the criteria.
	 */
	public List<Message> list(Integer numOf, String lang);
	
	/**
	 * Stores a message in the format input + from + country.
	 * @param input String representing first part of the message to be stored.
	 * @param country String representing second part of the message to be stored.
	 * @return Message stored.
	 */
	public Message submit(String input,String country);
}
