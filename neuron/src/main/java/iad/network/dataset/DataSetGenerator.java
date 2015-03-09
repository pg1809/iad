/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.dataset;

import iad.network.input.InputRow;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public interface DataSetGenerator {

    public List<InputRow> generateData(int samples);
}
