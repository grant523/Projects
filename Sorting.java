import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Sorting extends JFrame {
    JTextArea inputArea, outputArea;
    JRadioButton mergeButton, bubbleButton;

    public static void main(String args[]) {
        new Sorting();
    }

    public Sorting() {
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setTitle("Sorting");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel instruct = new JLabel("Enter a sequence of numbers separated by space: ");
        panel.add(instruct);

        inputArea = new JTextArea(5, 30);
        inputArea.setLineWrap(true);
        panel.add(inputArea);

        ButtonGroup buttons = new ButtonGroup();

        mergeButton = new JRadioButton("Merge Sort");
        buttons.add(mergeButton);
        mergeButton.setSelected(true);

        bubbleButton = new JRadioButton("Bubble Sort");
        buttons.add(bubbleButton);

        Box radioBox = Box.createVerticalBox();
        radioBox.add(mergeButton);
        radioBox.add(bubbleButton);
        panel.add(radioBox);

        JButton enter = new JButton("enter");
        ButtonListener button = new ButtonListener();
        enter.addActionListener(button);
        panel.add(enter);

        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        panel.add(outputArea);

        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputString = inputArea.getText();
            inputString = inputString.replaceAll("[^-?0-9]+", " ");
            String[] inputArray = inputString.trim().split("\\s");
            int[] nums = new int[inputArray.length];
            for (int i = 0; i < inputArray.length; i++) {
                nums[i] = Integer.parseInt(inputArray[i]);
            }
            if (mergeButton.isSelected()) {
                nums = mergeSort(nums);
                outputArea.setText(arrayToString(nums));
            } else if (bubbleButton.isSelected()) {
                bubbleSort(nums);
                outputArea.setText(arrayToString(nums));
            }
        }
    }

    int[] mergeSort(int[] a) {
        int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
        int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
        if (left.length > 1) {
            left = mergeSort(left);
        }
        if (right.length > 1) {
            right = mergeSort(right);
        }
        return merge(left, right);
    }

    int[] merge(int[] a, int[] b) {
        int[] temp = new int[a.length + b.length];
        int aPos = 0;
        int bPos = 0;
        for (int i = 0; i < temp.length; i++) {
            if (aPos > a.length - 1) {
                temp[i] = b[bPos];
                bPos++;
            }
            else if (bPos > b.length - 1) {
                temp[i] = a[aPos];
                aPos++;
            }
            else if (a[aPos] < b[bPos]) {
                temp[i] = a[aPos];
                aPos++;
            } else {
                temp[i] = b[bPos];
                bPos++;
            }
        }
        return temp;
    }

    int[] bubbleSort(int[] a) {
        boolean swapped = true;
        int temp;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }
        }
        return a;
    }

    String arrayToString(int[] a) {
        String output = "";
        boolean isFirst = true;
        for (int i = 0; i < a.length; i++) {
            if (!isFirst)
                output = output + " ";
            output = output + a[i];
            isFirst = false;
        }
        return output;
    }
}
