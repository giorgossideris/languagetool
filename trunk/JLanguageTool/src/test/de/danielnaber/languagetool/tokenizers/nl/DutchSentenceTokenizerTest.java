﻿/* LanguageTool, a natural language style checker 
 * Copyright (C) 2006 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package de.danielnaber.languagetool.tokenizers.nl;

import junit.framework.TestCase;
import de.danielnaber.languagetool.TestTools;

/**
 * @author Daniel Naber
 */
public class DutchSentenceTokenizerTest extends TestCase {

  // accept \n as paragraph:
  private DutchSentenceTokenizer stokenizer = new DutchSentenceTokenizer();
  // accept only \n\n as paragraph:
  private DutchSentenceTokenizer stokenizer2 = new DutchSentenceTokenizer();
  
  public void setUp() {
    stokenizer.setSingleLineBreaksMarksParagraph(true);  
    stokenizer2.setSingleLineBreaksMarksParagraph(false);  
  }

  public void testTokenize() {
    // NOTE: sentences here need to end with a space character so they
    // have correct whitespace when appended:
    testSplit(new String[] { "Dit is een zin." });
    testSplit(new String[] { "Dit is een zin. ", "Nog een." });
    testSplit(new String[] { "Een zin! ", "Nog een." });
    testSplit(new String[] { "Een zin... ", "Nog een." });
    testSplit(new String[] { "Op http://www.test.de vind je een website." });
    testSplit(new String[] { "De brief is op 3.10 gedateerd." });
    testSplit(new String[] { "De brief is op 31.1 gedateerd." });
    testSplit(new String[] { "De breif is op 3.10.2000 gedateerd." });

    testSplit(new String[] { "Vandaag is het 13.12.2004." });
    testSplit(new String[] { "Op 24.09 begint het." });
    testSplit(new String[] { "Om 17:00 begint het." });
    testSplit(new String[] { "In paragraaf 3.9.1 is dat beschreven." });

    testSplit(new String[] { "Januari jl. is dat vastgelegd." });
    testSplit(new String[] { "Appel en pruimen enz. werden gekocht." });
    testSplit(new String[] { "De afkorting n.v.t. betekent niet van toepassing." });

    testSplit(new String[] { "Bla et al. blah blah." });

    testSplit(new String[] { "Dat is,, of het is bla." });
    testSplit(new String[] { "Dat is het.. ", "Zo gaat het verder." });

    testSplit(new String[] { "Dit hier is een(!) zin." });
    testSplit(new String[] { "Dit hier is een(!!) zin." });
    testSplit(new String[] { "Dit hier is een(?) zin." });
    testSplit(new String[] { "Dit hier is een(???) zin." });
    testSplit(new String[] { "Dit hier is een(???) zin." });

    testSplit(new String[] { "»De papagaai is groen.« ",  "Dat was hij al." });
    testSplit(new String[] { "»De papagaai is groen«, zei hij." });

    // TODO, zin na dubbele punt
    testSplit(new String[] { "Dat was het: helmaal niets." });
    testSplit(new String[] { "Dat was het: het is een nieuwe zin." });
  }

  public void testSplit(String[] sentences) {
    TestTools.testSplit(sentences, stokenizer);
  }
  
}
