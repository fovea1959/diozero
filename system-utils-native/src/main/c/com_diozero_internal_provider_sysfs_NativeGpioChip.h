/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_diozero_internal_provider_sysfs_NativeGpioChip */

#ifndef _Included_com_diozero_internal_provider_sysfs_NativeGpioChip
#define _Included_com_diozero_internal_provider_sysfs_NativeGpioChip
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    getChips
 * Signature: ()Ljava/util/ArrayList;
 */
JNIEXPORT jobject JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_getChips
  (JNIEnv *, jclass);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    openChip
 * Signature: (Ljava/lang/String;)Lcom/diozero/internal/provider/sysfs/NativeGpioChip;
 */
JNIEXPORT jobject JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_openChip
  (JNIEnv *, jclass, jstring);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    provisionGpioInputDevice
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_provisionGpioInputDevice
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    provisionGpioOutputDevice
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_provisionGpioOutputDevice
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    getValue
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_getValue
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    setValue
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_setValue
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    epollCreate
 * Signature: ()I
 */
JNIEXPORT int JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_epollCreate
  (JNIEnv *, jclass);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    epollAddFileDescriptor
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_epollAddFileDescriptor
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    epollRemoveFileDescriptor
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_epollRemoveFileDescriptor
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    pollLoop
 * Signature: (IILcom/diozero/internal/provider/sysfs/GpioLineEventListener;)I
 */
JNIEXPORT void JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_eventLoop
  (JNIEnv *, jclass, jint, jint, jobject);

/*
 * Class:     com_diozero_internal_provider_sysfs_NativeGpioChip
 * Method:    close
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_diozero_internal_provider_sysfs_NativeGpioChip_close
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif
