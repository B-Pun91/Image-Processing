
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np
import scipy
import cv2
from scipy import misc

path = 'LenaRGB.bmp' # path for RGB image

path1 = 'Baboon.bmp' # path for GreyScale image

def imread1(path1): # function to read RGB image

    return cv2.imread(path1)

def imread(path): # function to read Greyscale image

    return misc.imread(path, flatten = False)

def Disp_Image(img, title):  # function to display image with its respective title

      plt.imshow(img, cmap='gray')
      plt.title(title)
      plt.show()

# Lab 5
def HistogramRGB(img, title):  # function to create and display histogram for RGB image
     color = ('b', 'g', 'r')
     for channel, col in enumerate(color):

         histr = cv2.calcHist([img], [channel], None, [256], [0, 256])
         plt.plot(histr, color=col)
         plt.xlim([0, 256])
     plt.title(title)
     plt.savefig("abc.png")
     plt.show()
     #print(histr)

def HistogramGreyScale(img,title):  #function to create and display histogram for GreyScale image

     vals = img.mean(axis=2).flatten()
     #plot histogram with 255 bins
     b, bins, patches = plt.hist(vals, 255)
     plt.xlim([0, 255])
     plt.title(title)
     plt.savefig("abc1.png")
     plt.show()
     mean = int(np.mean(b))
     std_deviation = int(np.std(b))
     print
     print 'Mean obtained from the Grey Scale Histogram', mean
     print
     print 'Standard Deviation obtained from the Grey Scale Histogram', std_deviation
     return (mean, std_deviation)

def HistogramEqualisation(img):  # Function to create histogram equalisation of an image


    img2equ = cv2.cvtColor(img, cv2.COLOR_BGR2YUV)
    img2equ[:, :, 0] = cv2.equalizeHist(img2equ[:, :, 0])
    hist_equalization_result = cv2.cvtColor(img2equ, cv2.COLOR_YUV2BGR)

    cv2.imwrite('Hist_Equalisation.jpg', hist_equalization_result)

    return(hist_equalization_result)

#Lab 7
def saltnpepper(img):  # fucntion that adds salt and pepper noise to an image

    saltnoise = np.random.choice([0,255], img.shape)
    peppernoise = np.random.choice([0,-255], img.shape)
    noiseImage = (img + saltnoise + peppernoise)
    cv2.imwrite('saltnpeppernoiseimage.jpg', noiseImage)
    return (noiseImage)

def simpleThresholding(img):  # Simple thresholding of an image
    #Threshold number chosen to be 77
    Image_threshold = Threshold(img, [77])
    cv2.imwrite('SimpleThreshold.jpg', Image_threshold)

    return(Image_threshold)

def Threshold(Temp_img, threshold_value):
    Temp_img1 = np.array(Temp_img, copy =True)
    Temp_img1[Temp_img1 < threshold_value ]= 0
    Temp_img1[Temp_img1 > threshold_value] = 225
    return(Temp_img1)

def automatedThresholding(img): # automated thresholding of an image

    mean = mean = int(np.mean(img))
    Image_threshold = Threshold(img, mean)
    cv2.imwrite('AutomatedThreshold.jpg', Image_threshold)
    return(Image_threshold)

def adaptiveThresholding(img): # adaptive thresholding of an image


    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    adv_thresh = cv2.adaptiveThreshold(gray_img,255,cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY,9,4)
    cv2.imwrite('AdaptiveThreshold.jpg', adv_thresh)
    return(adv_thresh)

image = imread(path)

image1 = imread1(path1)

#Disp_Image(image1, 'grey_image')

#Disp_Image(image, 'RGB Image')

HistogramRGB(image, 'Histogram for RGB image')

mean, std = HistogramGreyScale(image, 'Histogram of Grey Scale Image')

image2 = HistogramEqualisation(image1)

#Disp_Image(image2, 'HistogramEqualisation')

Image3 = saltnpepper(image1)

#Disp_Image (Image3, 'Image with salt and pepper noise')

Image4 = simpleThresholding(image1)

#Disp_Image(Image4, 'Simple Threshold Image')

Image5 = automatedThresholding(image1)

#Disp_Image(Image5, 'Automated Threshold Image')

Image6 = adaptiveThresholding(image1)

#Disp_Image(Image6, 'Adaptive Threshold Image')