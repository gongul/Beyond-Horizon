package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import test.CellPhone;
import test.FamilyMember;
import test.MobileVendor;
import test.CellPhoneSerializer;
import test.FamilyMemberSerializer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by HomoEfficio on 2016-10-22.
 */
public class CustomSerializationMain {

	public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = getObjectMapper();

        FamilyMember õ�ϴ��屺 = getFamilyMember();

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(õ�ϴ��屺));
    }

    private static ObjectMapper getObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(FamilyMember.class, new FamilyMemberSerializer());
        simpleModule.addSerializer(CellPhone.class, new CellPhoneSerializer());

        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }

    private static FamilyMember getFamilyMember() {

        FamilyMember �ճ��1 = new FamilyMember(
                31L, "�ճ��", null, null
        );
        FamilyMember ����2 = new FamilyMember(
                32L, "����2", null, null
        );

        Set<FamilyMember> childrens1 = new LinkedHashSet<>();
        childrens1.add(�ճ��1);
        childrens1.add(����2);
        FamilyMember �Ƶ�1 = new FamilyMember(
                21L, "�Ƶ�1", new CellPhone("01087879898", MobileVendor.KT), childrens1
        );
        FamilyMember �����1 = new FamilyMember(
                22L, "�����1", new CellPhone("01082825353", MobileVendor.SKT), childrens1
        );

        Set<FamilyMember> childrens = new LinkedHashSet<>();
        childrens.add(�Ƶ�1);
        childrens.add(�����1);
        return new FamilyMember(
                21L, "õ�ϴ��屺", new CellPhone("01056561253", MobileVendor.LGT), childrens
        );
    }
}
