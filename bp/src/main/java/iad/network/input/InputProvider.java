/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.input;

import iad.network.AbstractNetwork;

/**
 *
 * @author Wojciech Szałapski
 */
public interface InputProvider {
    
    public boolean hasMoreRows();
    
    public void provideInputRow(AbstractNetwork network);
}
