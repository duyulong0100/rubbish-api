package Models;

public enum CommonStatus {

    IsSuccess,

    PartnerErr,
    // / <summary>
    // / ������IP ��֤ʧ�� 110
    // / </summary>
    PartnerIPErr,

    // / <summary>
    // / ǩ������
    // / </summary>
    SignErr,

    // / <summary>
    // / ������������
    // / </summary>
    DataErr,

    // / <summary>
    // / ���ݲ�������
    // / </summary>
    ArgErr,

    // / <summary>
    // / �ڲ���������쳣
    // / </summary>
    InnerException,

    // / <summary>
    // / ��������ʧ�ܣ����ж�Ӧ����ϸ����ʧ����Ϣ
    // / </summary>
    SaveDataErr,

    // / <summary>
    // / ��֤����������ʧ��
    // / </summary>
    VerifyDataErr

}
